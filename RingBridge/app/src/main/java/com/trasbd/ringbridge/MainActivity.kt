package com.trasbd.ringbridge

import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.*
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.trasbd.ringbridge.ui.theme.RingBridgeTheme
import java.util.UUID
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
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
    ) { results ->
        val allGranted = results.values.all { it }
        if (allGranted) {
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
            Log.d(
                "RingBridge", "onConnectionStateChange status=$status newState=$newState"
            )

            if (newState == BluetoothProfile.STATE_CONNECTED) {
                Log.d("RingBridge", "✅ Connected, discovering services")
                isConnected = true
                gatt.discoverServices()
            } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                isConnected = false
                isReady = false
                Log.d("RingBridge", "❌ Disconnected")

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
                            Log.d("RingBridge", "✅ BE94 write characteristic ready")
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

        @RequiresApi(Build.VERSION_CODES.TIRAMISU)
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
                Log.e("RingBridge", "❌ CCCD not found")
            }
        }

        @RequiresApi(Build.VERSION_CODES.TIRAMISU)
        @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
        private fun writeNextCccd() {
            val (d, v) = cccdQueue.removeFirstOrNull() ?: run {
                cccdWriting = false
                Log.d("RingBridge", "✅ All CCCDs written")
                return
            }

            cccdWriting = true
            bluetoothGatt.writeDescriptor(d, v)
        }


        @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
        @RequiresApi(Build.VERSION_CODES.TIRAMISU)
        override fun onDescriptorWrite(
            gatt: BluetoothGatt, descriptor: BluetoothGattDescriptor, status: Int
        ) {
            if (status == BluetoothGatt.GATT_SUCCESS) {
                writeNextCccd()
            } else {
                Log.e("RingBridge", "❌ CCCD write failed: $status")
            }
        }

        @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
        override fun onCharacteristicChanged(
            gatt: BluetoothGatt, characteristic: BluetoothGattCharacteristic, value: ByteArray
        ) {
            Log.d(
                "RingBridge", "NOTIFY ${characteristic.uuid}: ${
                    value.joinToString(" ") { "%02X".format(it) }
                }")

            // Fire-and-forget coroutine (matches asyncio.create_task)
            CoroutineScope(Dispatchers.IO).launch {
                rxMutex.withLock {
                    if (value.isEmpty() || value.size < 4) return@withLock

                    val full = reassembleFrame(value) ?: return@withLock
                    val frame = decodeFrame(full)
                    Log.d(
                        "RingBridge",
                        "Received ${frame.group} ${frame.subtype} ${
                            frame.payload.joinToString(" ") {
                                "%02X".format(it)
                            }
                        }"
                    )
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

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    private fun handleFrame(group: Int, subtype: Int, payload: ByteArray) {
        var popped = false
        if (sendQueue.count() > 0) {
            val head = sendQueue[0]
            if (head.group == group && head.subtype == subtype) {
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

    private fun handleGroup5(subtype: Int, payload: ByteArray) {
        val session = healthSession ?: HealthSession().also {
            healthSession = it
        }

        session.ingest(subtype, payload)

        if (session.complete) {
            healthSession = null
            val data = session.parse()
            Log.d("RingBridge", data.toString())

            sendToHealthConnect(subtype, data)



        }
    }

    fun sendToHealthConnect(subtype: Int, data: Any)
    {
        when(subtype){
            HealthSession.SLEEP_HEALTH_TYPE -> sendSleepToHealthConnect(data)
            HealthSession.ALL_HEALTH_TYPE -> sendAllToHealthConnect(data)
        }
    }

    fun sendAllToHealthConnect(data: Any)
    {

    }

    fun sendSleepToHealthConnect(data: Any)
    {

    }


    @SuppressLint("ObsoleteSdkInt")
    private fun requestBluetoothPermissions()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
            permissionLauncher.launch(blePermissions)
    }

    private fun requestHealthConnectPermissions() {
        TODO("Not yet implemented")
    }


    // =====================
    // Activity lifecycle
    // =====================
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


            requestBluetoothPermissions()


        enableEdgeToEdge()
        setContent {
            RingBridgeTheme {
                MainScreen(
                    isConnected = isConnected,
                    isReady = isReady,
                    onRequestData = { requestHealthData() },
                    onRequestBluetooth = { requestBluetoothPermissions()},
                    onRequestHealthConnect = {requestHealthConnectPermissions()},
                    onConnect = { startBle() })
            }
        }

    }

    // =====================
    // BLE start
    // =====================
    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    private fun startBle() {
        Log.d("RingBridge", "startBle() called")

        val bluetoothManager = getSystemService(BLUETOOTH_SERVICE) as BluetoothManager

        val adapter = bluetoothManager.adapter
        if (adapter == null || !adapter.isEnabled) {
            Log.e("RingBridge", "Bluetooth adapter not available or disabled")
            return
        }

        val device = adapter.getRemoteDevice(RING_MAC)
        Log.d("RingBridge", "Connecting to $RING_MAC")

        bluetoothGatt = device.connectGatt(
            this, false,              // do NOT autoConnect
            gattCallback, BluetoothDevice.TRANSPORT_LE
        )
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    private fun requestHealthData() {
        if (!isReady) {
            Log.e("RingBridge", "❌ Not ready yet")
            return
        }


        //send(0x0504) // 1284 sleep
        SendCmd(1289)
    }

    @Suppress("ArrayInDataClass")
    private data class PendingCommand(
        val cmd: Int, val group: Int, val subtype: Int, val payload: ByteArray = byteArrayOf()
    )

    private val sendQueue = ArrayDeque<PendingCommand>()

    @Suppress("FunctionName")
    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun SendCmd(cmd: Int, payload: ByteArray = byteArrayOf()) {
        val pending = PendingCommand(cmd, cmd shr 8 and 0xFF, cmd and 0xFF, payload)
        sendQueue.add(pending)
        if (sendQueue.count() == 1) {
            SendPending(pending)
        }
    }

    @Suppress("FunctionName")
    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun SendPending(pending: PendingCommand) {
        val frame = buildBe94Frame(pending.cmd, pending.payload)
        bluetoothGatt.writeCharacteristic(
            be94WriteChar,
            frame,
            BluetoothGattCharacteristic.WRITE_TYPE_DEFAULT
        )
        cmdAck = false
        Log.d("RingBridge", "Sent ${pending.cmd} " + frame.joinToString(" ") { "%02X".format(it) })

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

            val SLEEP_TYPES = mutableMapOf<Int, String>(
                241 to "Deep Sleep", 242 to "Light Sleep", 243 to "REM"
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
            val type = healthType
                ?: throw IllegalStateException("No health session active")

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
            raw: ByteArray,
            healthType: Int
        ): HealthHistoryResult {


            // Match Python: time.localtime().tm_gmtoff
            val tzOffsetMs = TimeZone.getDefault().rawOffset.toLong()

            val records = mutableListOf<HealthHistoryRecord>()

            val RECORD_LEN = 20
            var i = 0

            val dateFormat = SimpleDateFormat("yyyyMMdd HHmmss", Locale.US)

            while (i + RECORD_LEN <= raw.size) {

                // ---- timestamp ----
                val tsSec =
                    (raw[i].toInt() and 0xFF) or
                            ((raw[i + 1].toInt() and 0xFF) shl 8) or
                            ((raw[i + 2].toInt() and 0xFF) shl 16) or
                            ((raw[i + 3].toInt() and 0xFF) shl 24)

                val startTime =
                    ((tsSec.toLong() + OFFSET_2000) * 1000L) - tzOffsetMs

                val startDateTime =
                    dateFormat.format(Date(startTime))

                // ---- fields (EXACT mapping) ----
                val stepValue =
                    (raw[i + 4].toInt() and 0xFF) or
                            ((raw[i + 5].toInt() and 0xFF) shl 8)

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

                i += RECORD_LEN
            }

            return HealthHistoryResult(
                dataType = healthType,
                data = records
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
                    (raw[i + 2].toInt() and 0xFF) or
                            ((raw[i + 3].toInt() and 0xFF) shl 8)

                val startSec =
                    (raw[i + 4].toInt() and 0xFF) or
                            ((raw[i + 5].toInt() and 0xFF) shl 8) or
                            ((raw[i + 6].toInt() and 0xFF) shl 16) or
                            ((raw[i + 7].toInt() and 0xFF) shl 24)

                val endSec =
                    (raw[i + 8].toInt() and 0xFF) or
                            ((raw[i + 9].toInt() and 0xFF) shl 8) or
                            ((raw[i + 10].toInt() and 0xFF) shl 16) or
                            ((raw[i + 11].toInt() and 0xFF) shl 24)

                val startTime =
                    ((startSec.toLong() + OFFSET_2000) * 1000L) - tzOffsetMs

                val endTime =
                    ((endSec.toLong() + OFFSET_2000) * 1000L) - tzOffsetMs

                val startDateTime = dateFormat.format(Date(startTime))
                val endDateTime = dateFormat.format(Date(endTime))

                val deepSleepCount =
                    (raw[i + 12].toInt() and 0xFF) or
                            ((raw[i + 13].toInt() and 0xFF) shl 8)

                // ---- dual interpretation block (EXACT Java behavior) ----
                val remTotal: Int
                val deepTotal: Int
                val lightTotal: Int
                val lightCount: Int

                if (deepSleepCount == 0xFFFF) {
                    remTotal =
                        (raw[i + 14].toInt() and 0xFF) or
                                ((raw[i + 15].toInt() and 0xFF) shl 8)

                    deepTotal =
                        (raw[i + 16].toInt() and 0xFF) or
                                ((raw[i + 17].toInt() and 0xFF) shl 8)

                    lightTotal =
                        (raw[i + 18].toInt() and 0xFF) or
                                ((raw[i + 19].toInt() and 0xFF) shl 8)

                    lightCount = 0
                } else {
                    lightCount =
                        (raw[i + 14].toInt() and 0xFF) or
                                ((raw[i + 15].toInt() and 0xFF) shl 8)

                    remTotal = 0

                    deepTotal =
                        ((raw[i + 16].toInt() and 0xFF) or
                                ((raw[i + 17].toInt() and 0xFF) shl 8)) * 60

                    lightTotal =
                        ((raw[i + 18].toInt() and 0xFF) or
                                ((raw[i + 19].toInt() and 0xFF) shl 8)) * 60
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
                        (raw[segPtr + 1].toInt() and 0xFF) or
                                ((raw[segPtr + 2].toInt() and 0xFF) shl 8) or
                                ((raw[segPtr + 3].toInt() and 0xFF) shl 16) or
                                ((raw[segPtr + 4].toInt() and 0xFF) shl 24)

                    val segTime =
                        ((segSec.toLong() + OFFSET_2000) * 1000L) - tzOffsetMs

                    val dur =
                        (raw[segPtr + 5].toInt() and 0xFF) or
                                ((raw[segPtr + 6].toInt() and 0xFF) shl 8) or
                                ((raw[segPtr + 7].toInt() and 0xFF) shl 16)

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
                dataType = healthType,
                data = sessions
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
            val code: Int = 0,
            val dataType: Int,
            val data: List<HealthHistoryRecord>
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
            val code: Int = 0,
            val dataType: Int,
            val data: List<SleepSession>
        )


    }


}



@Composable
fun MainScreen(
    isConnected: Boolean,
    isReady: Boolean,
    onRequestData: () -> Unit,
    onRequestBluetooth: () -> Unit,
    onRequestHealthConnect: ()-> Unit,
    onConnect: () -> Unit
) {
    Scaffold { padding ->
        Column(
            modifier = Modifier.padding(padding), verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = onRequestBluetooth
            ) {
                Text("Bluetooth Permissions")
            }
            Button(
                onClick = onRequestHealthConnect
            ) {
                Text("HealthConnect Permissions")
            }
            Text(text = if (isConnected) "Connected" else "Not connected")

            Button(
                onClick = onConnect, enabled = !isReady
            ) {
                Text("Connect to Ring")
            }
            Button(
                onClick = onRequestData, enabled = isReady
            ) {
                Text("Get Ring Data")
            }
        }
    }
}
