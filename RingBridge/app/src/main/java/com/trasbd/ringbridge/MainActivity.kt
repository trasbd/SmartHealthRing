
package com.trasbd.ringbridge

import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.*
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresPermission
import com.trasbd.ringbridge.ui.theme.RingBridgeTheme
import java.util.UUID
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
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
import com.trasbd.ringbridge.ble.FrameCodec
import com.trasbd.ringbridge.ble.FrameCodec.buildBe94Frame
import com.trasbd.ringbridge.ble.PendingCommand
import com.trasbd.ringbridge.ble.RingUuids
import com.trasbd.ringbridge.healthconnect.HealthConnectWriter
import com.trasbd.ringbridge.protocol.HealthSession
import com.trasbd.ringbridge.ui.MainScreen
import com.trasbd.ringbridge.ui.uiLogger.UiLogger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.time.Instant

class MainActivity : ComponentActivity() {

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
                if (service.uuid == RingUuids.UUID_BE94_SERVICE) {
                    for (ch in service.characteristics) {
                        if (ch.uuid == RingUuids.UUID_BE94_WRITE) {
                            be94WriteChar = ch
                            isReady = true
                            logger.log("RingBridge", "✅ BE94 write characteristic ready")
                        }
                    }
                }

                for (ch in service.characteristics) {
                    if (ch.uuid !in RingUuids.NOTIFY_ALLOWLIST) continue

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
                    val frame = FrameCodec.decodeFrame(full)
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
            var ret: Boolean
            lifecycleScope.launch {
                if(healthWriter.write(data))
                {
                    when (data){
                        is HealthSession.SleepResult -> SendCmd(HealthSession.DELETE_SLEEP_CMD,
                            HealthSession.DELETE_PAYLOAD)
                        is HealthSession.HealthHistoryResult -> HealthSession.DELETE_HEALTH_CMD.forEach { SendCmd(it,
                            HealthSession.DELETE_PAYLOAD) }
                    }
                }
            }




        }
    }

    private lateinit var healthWriter: HealthConnectWriter


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





    val logger = UiLogger()




}








