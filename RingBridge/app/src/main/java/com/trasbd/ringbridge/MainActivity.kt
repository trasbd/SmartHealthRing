@file:Suppress("SpellCheckingInspection")

package com.trasbd.ringbridge

import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.*
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresPermission
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.trasbd.ringbridge.ui.theme.RingBridgeTheme
import java.util.UUID
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.core.app.ActivityCompat
import androidx.health.connect.client.HealthConnectClient
import androidx.health.connect.client.PermissionController
import androidx.health.connect.client.permission.HealthPermission
import androidx.health.connect.client.records.*
import androidx.health.connect.client.records.metadata.Device
import androidx.health.connect.client.records.metadata.Device.Companion.TYPE_RING
import androidx.health.connect.client.records.metadata.Metadata
import androidx.health.connect.client.units.Percentage
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.Date
import java.util.TimeZone
import java.util.Locale

class MainActivity : ComponentActivity() {

    @Suppress("ClassName", "unused")
    private object RING_UUIDS {
        val UUID_BE94_SERVICE: UUID = UUID.fromString("be940000-7333-be46-b7ae-689e71722bd5")

        val UUID_BE94_WRITE: UUID = UUID.fromString("be940001-7333-be46-b7ae-689e71722bd5")
        val UUID_BE94_WRITE2: UUID = UUID.fromString("be940002-7333-be46-b7ae-689e71722bd5")
        val UUID_IND_BE94_SECOND: UUID = UUID.fromString("be940003-7333-be46-b7ae-689e71722bd5")

        val UUID_NOTIFY_AE02: UUID = UUID.fromString("0000ae02-0000-1000-8000-00805f9b34fb")
        val UUID_IND_FEA1: UUID = UUID.fromString("0000fea1-0000-1000-8000-00805f9b34fb")
        val UUID_IND_FEA2: UUID = UUID.fromString("0000fea2-0000-1000-8000-00805f9b34fb")

        val UUID_NOTIFY_NUS_TX: UUID = UUID.fromString("6e400003-b5a3-f393-e0a9-e50e24dcca9e")

        val NOTIFY_ALLOWLIST = setOf(
            UUID_BE94_WRITE,
            UUID_IND_BE94_SECOND,
            UUID_NOTIFY_AE02,
            UUID_IND_FEA2,
            UUID_NOTIFY_NUS_TX
        )
    }

    @Suppress("PrivatePropertyName")
    private val RING_MAC = "07:35:00:01:8A:EC"

    private lateinit var be94WriteChar: BluetoothGattCharacteristic

    private var isConnected by mutableStateOf(false)
    private var isReady by mutableStateOf(false)

    private var healthSession: HealthSession? = null


    // =====================
    // BLE permissions
    // =====================
    private val blePermissions = arrayOf(
        Manifest.permission.BLUETOOTH_CONNECT, Manifest.permission.BLUETOOTH_SCAN
    )

    @SuppressLint("MissingPermission")
    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    private val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { _ ->
        updateBlePermissionState()
        if (blePermissionState == BlePermissionState.GRANTED) {
            startBle()
        }

    }


    // =====================
    // BLE objects
    // =====================
    private lateinit var bluetoothGatt: BluetoothGatt

    private val gattCallback = object : BluetoothGattCallback() {
        private val cccdQueue = ArrayDeque<Pair<BluetoothGattDescriptor, ByteArray>>()
        private var cccdWriting = false

        @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
        override fun onConnectionStateChange(
            gatt: BluetoothGatt, status: Int, newState: Int
        ) {
            logger.log(
                "RingBridge", "onConnectionStateChange status=$status newState=$newState"
            )

            if (newState == BluetoothProfile.STATE_CONNECTED) {
                logger.log("RingBridge", "✅ Connected, discovering services")
                isConnected = true
                gatt.discoverServices()
            } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                isConnected = false
                isReady = false
                logger.log("RingBridge", "❌ Disconnected")

            }
        }


        @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
        override fun onServicesDiscovered(gatt: BluetoothGatt, status: Int) {

            for (service in gatt.services) {
                if (service.uuid == RING_UUIDS.UUID_BE94_SERVICE) {
                    for (ch in service.characteristics) {
                        if (ch.uuid == RING_UUIDS.UUID_BE94_WRITE) {
                            be94WriteChar = ch
                            isReady = true
                            logger.log("RingBridge", "✅ BE94 write characteristic ready")
                        }
                    }
                }

                for (ch in service.characteristics) {
                    if (ch.uuid !in RING_UUIDS.NOTIFY_ALLOWLIST) continue

                    val props = ch.properties
                    val canNotify = props and BluetoothGattCharacteristic.PROPERTY_NOTIFY != 0
                    val canIndicate = props and BluetoothGattCharacteristic.PROPERTY_INDICATE != 0

                    if (canNotify || canIndicate) {
                        enableNotifications(gatt, ch)
                    }
                }
            }
        }


        @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
        private fun enableNotifications(
            gatt: BluetoothGatt, characteristic: BluetoothGattCharacteristic
        ) {
            gatt.setCharacteristicNotification(characteristic, true)
            val props = characteristic.properties
            val canIndicate = props and BluetoothGattCharacteristic.PROPERTY_INDICATE != 0
            val cccd = characteristic.getDescriptor(
                UUID.fromString("00002902-0000-1000-8000-00805f9b34fb")
            )

            if (cccd != null) {

                val cccdValue = if (canIndicate) BluetoothGattDescriptor.ENABLE_INDICATION_VALUE
                else BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE

                cccdQueue.add(cccd to cccdValue)
                if (!cccdWriting) writeNextCccd()

            } else {
                logger.log("RingBridge", "❌ CCCD not found")
            }
        }


        @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
        private fun writeNextCccd() {
            val (d, v) = cccdQueue.removeFirstOrNull() ?: run {
                cccdWriting = false
                logger.log("RingBridge", "✅ All CCCDs written")
                return
            }

            cccdWriting = true
            bluetoothGatt.writeDescriptor(d, v)
        }


        @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
        override fun onDescriptorWrite(
            gatt: BluetoothGatt, descriptor: BluetoothGattDescriptor, status: Int
        ) {
            if (status == BluetoothGatt.GATT_SUCCESS) {
                writeNextCccd()
            } else {
                logger.log("RingBridge", "❌ CCCD write failed: $status")
            }
        }

        @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
        override fun onCharacteristicChanged(
            gatt: BluetoothGatt, characteristic: BluetoothGattCharacteristic, value: ByteArray
        ) {
            logger.log(
                "RingBridge", "NOTIFY ${characteristic.uuid}: ${
                value.joinToString(" ") { "%02X".format(it) }
            }")

            // Fire-and-forget coroutine (matches asyncio.create_task)
            CoroutineScope(Dispatchers.IO).launch {
                rxMutex.withLock {
                    if (value.isEmpty() || value.size < 4) return@withLock

                    val full = reassembleFrame(value) ?: return@withLock
                    val frame = decodeFrame(full)
                    logger.log(
                        "RingBridge", "Received ${frame.group} ${frame.subtype} ${
                        frame.payload.joinToString(" ") {
                            "%02X".format(it)
                        }
                    }")
                    handleFrame(frame.group, frame.subtype, frame.payload)
                }
            }
        }


    }

    // response stuff?
    private val rxMutex = Mutex()

    private var rxBuffer: ByteArray? = null
    private var rxFragmented: Boolean = false

    private val mtu = 185 // match Python

    private fun reassembleFrame(data: ByteArray): ByteArray? {
        if (data.size < 4) return null

        val expectedLen = (data[2].toInt() and 0xFF) or ((data[3].toInt() and 0xFF) shl 8)

        // Fast path: full frame arrived
        if (expectedLen == data.size) {
            return data
        }

        // Length mismatch path
        if (!rxFragmented && data.size != mtu - 3) {
            return null
        }

        rxFragmented = true

        if (rxBuffer == null) {
            rxBuffer = data.copyOf()
            return null
        }

        rxBuffer = rxBuffer!! + data

        if (rxBuffer!!.size < 4) return null

        val newExpected =
            (rxBuffer!![2].toInt() and 0xFF) or ((rxBuffer!![3].toInt() and 0xFF) shl 8)

        if (rxBuffer!!.size < newExpected) return null

        if (rxBuffer!!.size > newExpected) {
            rxBuffer = null
            rxFragmented = false
            return null
        }

        val full = rxBuffer
        rxBuffer = null
        rxFragmented = false
        return full
    }

    @Suppress("ArrayInDataClass")
    private data class DecodedFrame(
        val group: Int, val subtype: Int, val payload: ByteArray
    )

    private fun decodeFrame(data: ByteArray): DecodedFrame {
        val group = data[0].toInt() and 0xFF
        val subtype = data[1].toInt() and 0xFF


        val totalLen = (data[2].toInt() and 0xFF) or ((data[3].toInt() and 0xFF) shl 8)

        val payloadLen = totalLen - 6
        val payload = data.copyOfRange(4, 4 + payloadLen)

        return DecodedFrame(group, subtype, payload)
    }

    private var cmdAck: Boolean = false


    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    private fun handleFrame(group: Int, subtype: Int, payload: ByteArray) {
        var popped = false
        val cmd = (group shl 8) or subtype
        if (sendQueue.count() > 0) {
            val head = sendQueue[0]
            if ((head.group == group && head.subtype == subtype) || (cmd in 1301..<1400 && head.cmd in 1301..<1400)) {
                sendQueue.removeFirst()
                cmdAck = true
            }
        }

        when (group) {
            1 -> {
                handleGroup1(subtype, payload)
            }

            2 -> {
                handleGroup2(subtype, payload)
                popped = true
            }

            5 -> {
                handleGroup5(subtype, payload)
                if (subtype == HealthSession.END_SUBTYPE && cmdAck) {
                    popped = true
                }
                if (cmd in 1301..<1400) popped = true
            }
        }

        if (popped && sendQueue.count() > 0) {
            SendPending(sendQueue[0])
        }


    }

    private fun handleGroup1(subtype: Int, payload: ByteArray) {

    }

    private fun handleGroup2(subtype: Int, payload: ByteArray) {

    }

    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    private fun handleGroup5(subtype: Int, payload: ByteArray) {
        val session = healthSession ?: HealthSession().also {
            healthSession = it
        }

        session.ingest(subtype, payload)

        if (session.complete) {
            healthSession = null
            val data = session.parse()
            logger.log("RingBridge", data.toString())

            lifecycleScope.launch {
                sendToHealthConnect(data)
            }


        }
    }

    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    suspend fun sendToHealthConnect(data: Any) {
        when (data) {
            is HealthSession.SleepResult -> sendSleepToHealthConnect(data)
            is HealthSession.HealthHistoryResult -> sendAllToHealthConnect(data)
        }
    }

    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    suspend fun sendAllToHealthConnect(data: HealthSession.HealthHistoryResult) {
        val records = mutableListOf<Record>()

        data.data.forEach { session ->
            val start = Instant.ofEpochMilli(session.startTime)
            val end = Instant.ofEpochMilli(session.startTime + 1)

            if (end < Instant.now()) {

                val samples = mutableListOf(
                    HeartRateRecord.Sample(
                        start, session.heartValue.toLong()
                    )
                )

                val meta = Metadata.autoRecorded(Device(TYPE_RING))

                records.add(
                    OxygenSaturationRecord(
                        start, null, Percentage(session.ooValue.toDouble()), meta
                    )
                )
                records.add(HeartRateRecord(start, null, end, null, samples, meta))
                records.add(
                    HeartRateVariabilityRmssdRecord(
                        start, null, session.hrvValue.toDouble(), meta
                    )
                )
                records.add(
                    RespiratoryRateRecord(
                        start, null, session.respiratoryRateValue.toDouble(), meta
                    )
                )
            }
        }

        if (records.isEmpty()) {
            logger.log("RingBridge", "⚠️ No records to insert")
            return
        }

        if (postToHealthConnect(records)) {
            // ✅ Only delete after successful insert
            HealthSession.DELETE_HEALTH_CMD.forEach {
                SendCmd(it, HealthSession.DELETE_PAYLOAD)
            }
        }


    }

    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    suspend fun sendSleepToHealthConnect(data: HealthSession.SleepResult) {
        val sessions = mutableListOf<SleepSessionRecord>()
        data.data.forEach { it ->
            val segments = mutableListOf<SleepSessionRecord.Stage>()
            it.sleepData.forEach { iit ->
                val start = Instant.ofEpochMilli(iit.sleepStartTime)
                val end = Instant.ofEpochMilli(iit.sleepStartTime + (iit.sleepLen * 1000))
                val type = HealthSession.SLEEP_TYPES[iit.sleepType]!!
                if (start < end) {
                    val currentSegment = SleepSessionRecord.Stage(start, end, type)
                    segments.add(currentSegment)
                }
            }

            /*
            if (it.wakeCount > 0)
            {
                val start = Instant.ofEpochMilli(it.startTime - (it.wakeDuration*1000))
                val end = Instant.ofEpochMilli(it.startTime)
                val type = SleepSessionRecord.STAGE_TYPE_AWAKE
                segments.add(SleepSessionRecord.Stage(start, end, type))
            }
            val start = Instant.ofEpochMilli(it.startTime - (it.wakeDuration*1000))
             */

            val start = Instant.ofEpochMilli(it.startTime)
            val end = Instant.ofEpochMilli(it.endTime)
            val meta = Metadata.autoRecorded(Device(TYPE_RING))
            sessions.add(SleepSessionRecord(start, null, end, null, meta, null, null, segments))

        }


        if (sessions.isEmpty()) {
            logger.log("RingBridge", "⚠️ No records to insert")
            return
        }


        if (postToHealthConnect(sessions)) {
            // ✅ Only delete after successful insert
            SendCmd(HealthSession.DELETE_SLEEP_CMD, HealthSession.DELETE_PAYLOAD)
        }


    }

    private suspend fun postToHealthConnect(records: List<Record>): Boolean {
        try {
            healthConnectClient.insertRecords(records)
            logger.log("RingBridge", "✅ Posted ${records.size} records")


        } catch (e: Exception) {
            logger.log("RingBridge", "❌ Health Connect insert failed: ${e.message}")
            e.printStackTrace()
            return false
        }
        return true

    }


    override fun onResume() {
        super.onResume()
        updateBlePermissionState()
    }

    enum class BlePermissionState {
        GRANTED, DENIED, PERMANENTLY_DENIED
    }

    private fun updateBlePermissionState() {

        val connectGranted =
            checkSelfPermission(Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_GRANTED

        val scanGranted =
            checkSelfPermission(Manifest.permission.BLUETOOTH_SCAN) == PackageManager.PERMISSION_GRANTED

        blePermissionState = when {
            connectGranted && scanGranted -> BlePermissionState.GRANTED

            ActivityCompat.shouldShowRequestPermissionRationale(
                this, Manifest.permission.BLUETOOTH_CONNECT
            ) -> BlePermissionState.DENIED

            else -> BlePermissionState.PERMANENTLY_DENIED
        }
    }

    @Suppress("PrivatePropertyName")
    private val PERMISSIONS = setOf(
        // Steps
        HealthPermission.getReadPermission(StepsRecord::class),
        HealthPermission.getWritePermission(StepsRecord::class),

        // Heart rate
        HealthPermission.getReadPermission(HeartRateRecord::class),
        HealthPermission.getWritePermission(HeartRateRecord::class),

        // Sleep
        HealthPermission.getReadPermission(SleepSessionRecord::class),
        HealthPermission.getWritePermission(SleepSessionRecord::class),

        // Blood oxygen / SpO₂
        HealthPermission.getReadPermission(OxygenSaturationRecord::class),
        HealthPermission.getWritePermission(OxygenSaturationRecord::class),

        HealthPermission.getReadPermission(HeartRateVariabilityRmssdRecord::class),
        HealthPermission.getWritePermission(HeartRateVariabilityRmssdRecord::class),

        HealthPermission.getReadPermission(RespiratoryRateRecord::class),
        HealthPermission.getWritePermission(RespiratoryRateRecord::class),

        )

    var healthConnectPermissionState: Boolean = false

    // Create the permissions launcher
    val requestPermissionActivityContract =
        PermissionController.createRequestPermissionResultContract()

    val requestPermissions =
        registerForActivityResult(requestPermissionActivityContract) { granted ->

            val missing = PERMISSIONS - granted
            healthConnectPermissionState = missing.isEmpty()

            if (healthConnectPermissionState) {
                logger.log("RingBridge", "HealthConnect permissions granted")
            } else {
                val missingNames = missing.joinToString("\n\t") { it }

                logger.log(
                    "RingBridge",
                    "HealthConnect permission denied\nMissing:\n\t$missingNames"
                )

            }
        }


    suspend fun checkPermissionsAndRun(healthConnectClient: HealthConnectClient) {
        val granted = healthConnectClient.permissionController.getGrantedPermissions()
        if (granted.containsAll(PERMISSIONS)) {
            // Permissions already granted; proceed with inserting or reading data
        } else {
            requestPermissions.launch(PERMISSIONS)
        }
    }

    private fun requestHealthConnectPermissions() {

        lifecycleScope.launch {
            checkPermissionsAndRun(healthConnectClient)
        }
    }


    private lateinit var healthConnectClient: HealthConnectClient


    private var blePermissionState by mutableStateOf(BlePermissionState.DENIED)

    private fun openAppSettings() {
        val intent = Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.fromParts("package", packageName, null)
        )
        startActivity(intent)
    }


    // =====================
    // Activity lifecycle
    // =====================
    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        permissionLauncher.launch(blePermissions)

        updateBlePermissionState()

        healthConnectClient = HealthConnectClient.getOrCreate(this)

        enableEdgeToEdge()
        setContent {
            RingBridgeTheme {
                MainScreen(
                    isConnected = isConnected,
                    isReady = isReady,
                    blePermissionState = blePermissionState,
                    healthConnectPermissionState = healthConnectPermissionState,
                    onRequestHealth = { requestHealthData() },
                    onRequestSleep = { requestSleepData() },
                    onRequestHealthConnect = { requestHealthConnectPermissions() },
                    onConnect = { startBle() },
                    onOpenSettings = { openAppSettings() },
                    logger = logger
                )
            }
        }

    }

    // =====================
    // BLE start
    // =====================
    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    private fun startBle() {
        logger.log("RingBridge", "startBle() called")

        val bluetoothManager = getSystemService(BLUETOOTH_SERVICE) as BluetoothManager

        val adapter = bluetoothManager.adapter
        if (adapter == null || !adapter.isEnabled) {
            logger.log("RingBridge", "Bluetooth adapter not available or disabled")
            return
        }

        val device = adapter.getRemoteDevice(RING_MAC)
        logger.log("RingBridge", "Connecting to $RING_MAC")

        bluetoothGatt = device.connectGatt(
            this, false,              // do NOT autoConnect
            gattCallback, BluetoothDevice.TRANSPORT_LE
        )
    }

    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    private fun requestHealthData() {
        if (!isReady) {
            logger.log("RingBridge", "❌ Not ready yet")
            return
        }
        SendCmd(1289)
    }

    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    private fun requestSleepData() {
        if (!isReady) {
            logger.log("RingBridge", "❌ Not ready yet")
            return
        }
        SendCmd(0x504)
    }


    @Suppress("ArrayInDataClass")
    private data class PendingCommand(
        val cmd: Int, val group: Int, val subtype: Int, val payload: ByteArray = byteArrayOf()
    )

    private val sendQueue = ArrayDeque<PendingCommand>()

    @Suppress("FunctionName")
    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    fun SendCmd(cmd: Int, payload: ByteArray = byteArrayOf()) {
        val pending = PendingCommand(cmd, cmd shr 8 and 0xFF, cmd and 0xFF, payload)
        sendQueue.add(pending)
        if (sendQueue.count() == 1) {
            SendPending(pending)
        }
    }

    @Suppress("FunctionName")
    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    private fun SendPending(pending: PendingCommand) {
        val frame = buildBe94Frame(pending.cmd, pending.payload)
        bluetoothGatt.writeCharacteristic(
            be94WriteChar, frame, BluetoothGattCharacteristic.WRITE_TYPE_DEFAULT
        )
        cmdAck = false
        logger.log(
            "RingBridge",
            "Sent ${pending.cmd} " + frame.joinToString(" ") { "%02X".format(it) })

    }

    fun buildBe94Frame(cmd: Int, payload: ByteArray): ByteArray {
        val totalLen = payload.size + 6
        val out = ByteArray(totalLen)

        out[0] = ((cmd shr 8) and 0xFF).toByte()
        out[1] = (cmd and 0xFF).toByte()
        out[2] = (totalLen and 0xFF).toByte()
        out[3] = ((totalLen shr 8) and 0xFF).toByte()

        // payload
        System.arraycopy(payload, 0, out, 4, payload.size)

        // CRC over header + payload
        val crc = crc16Ycbt(out.copyOfRange(0, 4 + payload.size))

        out[4 + payload.size] = (crc and 0xFF).toByte()
        out[5 + payload.size] = ((crc shr 8) and 0xFF).toByte()

        return out
    }


    fun crc16Ycbt(data: ByteArray, seed: Int = 0xFFFF): Int {
        var s = seed and 0xFFFF

        for (b in data) {
            val byte = b.toInt() and 0xFF

            val swapped = ((s shl 8) and 0xFF00) or ((s ushr 8) and 0x00FF)
            var s2 = swapped xor byte

            s2 = s2 xor ((s2 and 0xFF) ushr 4)
            val s3 = s2 xor ((s2 shl 12) and 0xFFFF)

            s = s3 xor (((s3 and 0xFF) shl 5) and 0xFFFF)
            s = s and 0xFFFF
        }

        return s
    }

    @Suppress("unused")
    fun chunkForMtu(data: ByteArray, mtu: Int): List<ByteArray> {
        val usable = mtu - 3
        val chunks = mutableListOf<ByteArray>()

        var i = 0
        while (i < data.size) {
            val end = minOf(i + usable, data.size)
            chunks.add(data.copyOfRange(i, end))
            i += usable
        }

        return chunks
    }

    class HealthSession() {
        companion object {
            const val SLEEP_HEALTH_TYPE = 4
            const val ALL_HEALTH_TYPE = 9
            val HEALTH_TYPES = intArrayOf(SLEEP_HEALTH_TYPE, 8, ALL_HEALTH_TYPE)
            
            const val END_SUBTYPE = 128
            const val END_COMMAND = 1408
            val END_PAYLOAD = byteArrayOf()

            val DELETE_PAYLOAD = byteArrayOf(0x02)
            val DELETE_HEALTH_CMD = listOf(1344, 1346, 1347, 1348)
            const val DELETE_SLEEP_CMD = 1345

            val SLEEP_TYPES = mutableMapOf<Int, Int>(
                241 to SleepSessionRecord.STAGE_TYPE_DEEP,
                242 to SleepSessionRecord.STAGE_TYPE_LIGHT,
                243 to SleepSessionRecord.STAGE_TYPE_REM,
                244 to SleepSessionRecord.STAGE_TYPE_AWAKE
            )

            const val OFFSET_2000 = 946684800L
        }

        var healthType: Int? = null
        var blocks = mutableListOf<ByteArray>()
        var complete: Boolean = false

        fun ingest(subtype: Int, payload: ByteArray) {
            if (HEALTH_TYPES.contains(subtype)) {
                healthType = subtype
                blocks = mutableListOf()
                complete = false
                return
            }

            if (healthType == null) {
                return
            }
            blocks.add(payload)

            if (subtype == END_SUBTYPE) {
                complete = true
            }
        }

        fun parse(): Any {
            val type = healthType ?: throw IllegalStateException("No health session active")

            // Concatenate payload blocks
            val totalLen = blocks.sumOf { it.size }
            val raw = ByteArray(totalLen)

            var offset = 0
            for (b in blocks) {
                System.arraycopy(b, 0, raw, offset, b.size)
                offset += b.size
            }

            return unpackHealthData(raw, type)
        }

        fun unpackHealthData(raw: ByteArray, healthType: Int): Any {
            return when (healthType) {
                SLEEP_HEALTH_TYPE -> unpackSleepData(raw, healthType)
                ALL_HEALTH_TYPE -> unpackHealthHistoryAll(raw, healthType)
                else -> throw NotImplementedError(
                    "Health type $healthType not implemented yet"
                )
            }
        }


        @Suppress("unused", "UnusedVariable")
        fun unpackHealthHistoryAll(
            raw: ByteArray, healthType: Int
        ): HealthHistoryResult {


            // Match Python: time.localtime().tm_gmtoff
            val tzOffsetMs = TimeZone.getDefault().rawOffset.toLong()

            val records = mutableListOf<HealthHistoryRecord>()

            val recordLength = 20
            var i = 0

            val dateFormat = SimpleDateFormat("yyyyMMdd HHmmss", Locale.US)

            while (i + recordLength <= raw.size) {

                // ---- timestamp ----
                val tsSec =
                    (raw[i].toInt() and 0xFF) or ((raw[i + 1].toInt() and 0xFF) shl 8) or ((raw[i + 2].toInt() and 0xFF) shl 16) or ((raw[i + 3].toInt() and 0xFF) shl 24)

                val startTime = ((tsSec.toLong() + OFFSET_2000) * 1000L) - tzOffsetMs

                val startDateTime = dateFormat.format(Date(startTime))

                // ---- fields (EXACT mapping) ----
                val stepValue =
                    (raw[i + 4].toInt() and 0xFF) or ((raw[i + 5].toInt() and 0xFF) shl 8)

                val heartValue = raw[i + 6].toInt() and 0xFF
                val sbpValue = raw[i + 7].toInt() and 0xFF   // unused
                val dbpValue = raw[i + 8].toInt() and 0xFF   // unused
                val ooValue = raw[i + 9].toInt() and 0xFF
                val respiratoryRate = raw[i + 10].toInt() and 0xFF
                val hrvValue = raw[i + 11].toInt() and 0xFF
                val cvrrValue = raw[i + 12].toInt() and 0xFF
                val tempInt = raw[i + 13].toInt() and 0xFF
                val tempFloat = raw[i + 14].toInt() and 0xFF
                val bodyFatInt = raw[i + 15].toInt() and 0xFF // unused
                val bodyFatFloat = raw[i + 16].toInt() and 0xFF // unused
                val bloodSugar = raw[i + 17].toInt() and 0xFF // unused

                records.add(
                    HealthHistoryRecord(
                        startTime = startTime,
                        startDateTime = startDateTime,
                        stepValue = stepValue,
                        heartValue = heartValue,
                        ooValue = ooValue,
                        respiratoryRateValue = respiratoryRate,
                        hrvValue = hrvValue,
                        cvrrValue = cvrrValue,
                        tempIntValue = tempInt,
                        tempFloatValue = tempFloat,
                    )
                )

                i += recordLength
            }

            return HealthHistoryResult(
                dataType = healthType, data = records
            )
        }

        fun unpackSleepData(raw: ByteArray, healthType: Int): SleepResult {

            val tzOffsetMs = TimeZone.getDefault().rawOffset.toLong()

            val sessions = mutableListOf<SleepSession>()

            val dateFormat = SimpleDateFormat("yyyyMMdd HHmmss", Locale.US)

            var i = 0
            val length = raw.size

            while (i + 20 <= length) {

                val sessionStart = i

                // ---- session header ----
                val sessionLen =
                    (raw[i + 2].toInt() and 0xFF) or ((raw[i + 3].toInt() and 0xFF) shl 8)

                val startSec =
                    (raw[i + 4].toInt() and 0xFF) or ((raw[i + 5].toInt() and 0xFF) shl 8) or ((raw[i + 6].toInt() and 0xFF) shl 16) or ((raw[i + 7].toInt() and 0xFF) shl 24)

                val endSec =
                    (raw[i + 8].toInt() and 0xFF) or ((raw[i + 9].toInt() and 0xFF) shl 8) or ((raw[i + 10].toInt() and 0xFF) shl 16) or ((raw[i + 11].toInt() and 0xFF) shl 24)

                val startTime = ((startSec.toLong() + OFFSET_2000) * 1000L) - tzOffsetMs

                val endTime = ((endSec.toLong() + OFFSET_2000) * 1000L) - tzOffsetMs

                val startDateTime = dateFormat.format(Date(startTime))
                val endDateTime = dateFormat.format(Date(endTime))

                val deepSleepCount =
                    (raw[i + 12].toInt() and 0xFF) or ((raw[i + 13].toInt() and 0xFF) shl 8)

                // ---- dual interpretation block (EXACT Java behavior) ----
                val remTotal: Int
                val deepTotal: Int
                val lightTotal: Int
                val lightCount: Int

                if (deepSleepCount == 0xFFFF) {
                    remTotal =
                        (raw[i + 14].toInt() and 0xFF) or ((raw[i + 15].toInt() and 0xFF) shl 8)

                    deepTotal =
                        (raw[i + 16].toInt() and 0xFF) or ((raw[i + 17].toInt() and 0xFF) shl 8)

                    lightTotal =
                        (raw[i + 18].toInt() and 0xFF) or ((raw[i + 19].toInt() and 0xFF) shl 8)

                    lightCount = 0
                } else {
                    lightCount =
                        (raw[i + 14].toInt() and 0xFF) or ((raw[i + 15].toInt() and 0xFF) shl 8)

                    remTotal = 0

                    deepTotal =
                        ((raw[i + 16].toInt() and 0xFF) or ((raw[i + 17].toInt() and 0xFF) shl 8)) * 60

                    lightTotal =
                        ((raw[i + 18].toInt() and 0xFF) or ((raw[i + 19].toInt() and 0xFF) shl 8)) * 60
                }

                // ---- parse sleep segments ----
                val sleepSegments = mutableListOf<SleepSegment>()
                val seen = HashSet<Long>()
                var wakeCount = 0
                var wakeDuration = 0

                var segPtr = sessionStart + 20
                val sessionEnd = sessionStart + sessionLen

                while (segPtr + 8 <= sessionEnd) {

                    val sleepType = raw[segPtr].toInt() and 0xFF

                    val segSec =
                        (raw[segPtr + 1].toInt() and 0xFF) or ((raw[segPtr + 2].toInt() and 0xFF) shl 8) or ((raw[segPtr + 3].toInt() and 0xFF) shl 16) or ((raw[segPtr + 4].toInt() and 0xFF) shl 24)

                    val segTime = ((segSec.toLong() + OFFSET_2000) * 1000L) - tzOffsetMs

                    val dur =
                        (raw[segPtr + 5].toInt() and 0xFF) or ((raw[segPtr + 6].toInt() and 0xFF) shl 8) or ((raw[segPtr + 7].toInt() and 0xFF) shl 16)

                    if (sleepType == 244) { // wake
                        wakeCount++
                        wakeDuration += dur
                    }

                    if (!seen.contains(segTime)) {
                        sleepSegments.add(
                            SleepSegment(
                                sleepType = sleepType,
                                sleepStartTime = segTime,
                                sleepStartDateTime = dateFormat.format(Date(segTime)),
                                sleepLen = dur
                            )
                        )
                        seen.add(segTime)
                    }

                    segPtr += 8
                }

                sessions.add(
                    SleepSession(
                        startTime = startTime,
                        startDateTime = startDateTime,
                        endTime = endTime,
                        endDateTime = endDateTime,
                        deepSleepCount = deepSleepCount,
                        lightSleepCount = lightCount,
                        deepSleepTotal = deepTotal,
                        lightSleepTotal = lightTotal,
                        rapidEyeMovementTotal = remTotal,
                        sleepData = sleepSegments,
                        wakeCount = wakeCount,
                        wakeDuration = wakeDuration
                    )
                )

                i = segPtr // ⚠️ EXACT Java/Python behavior
            }

            return SleepResult(
                dataType = healthType, data = sessions
            )
        }


        data class HealthHistoryRecord(
            val startTime: Long,
            val startDateTime: String,
            val stepValue: Int,
            val heartValue: Int,
            val ooValue: Int,
            val respiratoryRateValue: Int,
            val hrvValue: Int,
            val cvrrValue: Int,
            val tempIntValue: Int,
            val tempFloatValue: Int,
        )

        data class HealthHistoryResult(
            val code: Int = 0, val dataType: Int, val data: List<HealthHistoryRecord>
        )

        data class SleepSegment(
            val sleepType: Int,
            val sleepStartTime: Long,
            val sleepStartDateTime: String,
            val sleepLen: Int
        )

        data class SleepSession(
            val startTime: Long,
            val startDateTime: String,
            val endTime: Long,
            val endDateTime: String,
            val deepSleepCount: Int,
            val lightSleepCount: Int,
            val deepSleepTotal: Int,
            val lightSleepTotal: Int,
            val rapidEyeMovementTotal: Int,
            val sleepData: List<SleepSegment>,
            val wakeCount: Int,
            val wakeDuration: Int
        )

        data class SleepResult(
            val code: Int = 0, val dataType: Int, val data: List<SleepSession>
        )


    }

    val logger = UiLogger()


    class UiLogger {
        private val _lines = mutableStateListOf<LogLine>()
        val lines: List<LogLine> = _lines

        fun log(level: String, msg: String) {
            Log.d("UiLogger", "$level: $msg")
            val ts = java.time.LocalTime.now().toString()
            _lines.add(LogLine(ts, level, msg))
        }

        fun clear() = _lines.clear()
    }


}

@Composable
fun MainScreen(
    blePermissionState: MainActivity.BlePermissionState,
    healthConnectPermissionState: Boolean,
    isConnected: Boolean,
    isReady: Boolean,
    onRequestHealthConnect: () -> Unit,
    onConnect: () -> Unit,
    onRequestHealth: () -> Unit,
    onRequestSleep: () -> Unit,
    onOpenSettings: () -> Unit,
    logger: MainActivity.UiLogger
) {
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Text(
                text = "Ring Bridge", style = MaterialTheme.typography.headlineMedium
            )

            /* ---------------- Bluetooth Permission ---------------- */

            Card {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Bluetooth Permission", style = MaterialTheme.typography.titleMedium
                    )

                    when (blePermissionState) {
                        MainActivity.BlePermissionState.GRANTED -> {
                            StatusRow("Bluetooth access granted", "✅")
                        }

                        MainActivity.BlePermissionState.DENIED -> {
                            StatusRow("Bluetooth permission required", "⚠️")
                            Text(
                                text = "Please allow Bluetooth access when prompted.",
                                style = MaterialTheme.typography.bodySmall
                            )
                        }

                        MainActivity.BlePermissionState.PERMANENTLY_DENIED -> {
                            StatusRow("Bluetooth permission denied", "❌")
                            Text(
                                text = "Enable Bluetooth permission in system settings.",
                                style = MaterialTheme.typography.bodySmall
                            )

                            Button(
                                onClick = onOpenSettings, modifier = Modifier.padding(top = 8.dp)
                            ) {
                                Text("Open App Settings")
                            }
                        }
                    }
                }
            }

            /* ---------------- Health Connect ---------------- */

            Card {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Health Connect", style = MaterialTheme.typography.titleMedium
                    )

                    Button(onClick = onRequestHealthConnect, enabled = !healthConnectPermissionState) {
                        Text("Grant Health Connect Permissions")
                    }
                }
            }

            /* ---------------- Ring Connection ---------------- */

            Card {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Ring Status", style = MaterialTheme.typography.titleMedium
                    )

                    StatusRow(
                        text = if (isConnected) "Ring connected" else "Not connected",
                        icon = if (isConnected) "🟢" else "🔴"
                    )

                    Button(
                        onClick = onConnect,
                        enabled = blePermissionState == MainActivity.BlePermissionState.GRANTED && !isConnected
                    ) {
                        Text("Connect to Ring")
                    }

                    Button(
                        onClick = onRequestHealth,
                        enabled = isReady && blePermissionState == MainActivity.BlePermissionState.GRANTED
                    ) {
                        Text("Get Health Data")
                    }

                    Button(
                        onClick = onRequestSleep,
                        enabled = isReady && blePermissionState == MainActivity.BlePermissionState.GRANTED
                    ) {
                        Text("Get Sleep Data")
                    }
                }
            }

            Card {
                LogConsole(
                    logs = logger.lines, modifier = Modifier.fillMaxWidth()
                )

            }
        }
    }
}

@Composable
private fun StatusRow(text: String, icon: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(icon)
        Text(text)
    }
}

data class LogLine(
    val time: String, val level: String, val message: String
)


@Composable
fun LogConsole(
    logs: List<LogLine>, modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState()

    LaunchedEffect(logs.size) {
        if (logs.isNotEmpty()) {
            listState.animateScrollToItem(logs.lastIndex)
        }
    }

    Card(modifier = modifier) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text("Console", style = MaterialTheme.typography.titleMedium)

            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

            LazyColumn(
                state = listState, modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
            ) {
                items(logs) { line ->
                    Text(
                        text = "[${line.time}] ${line.level}: ${line.message}",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}
