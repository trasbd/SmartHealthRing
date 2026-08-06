package com.trasbd.ringbridge.ble

import android.Manifest
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothGattCallback
import android.bluetooth.BluetoothGattCharacteristic
import android.bluetooth.BluetoothGattConnectionSettings
import android.bluetooth.BluetoothGattDescriptor
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothProfile
import android.content.Context
import android.content.Context.BLUETOOTH_SERVICE
import android.os.Build
import androidx.annotation.RequiresPermission
import com.trasbd.lib.ILogger
import com.trasbd.ringbridge.ble.FrameCodec.buildBe94Frame
import com.trasbd.ringbridge.ble.FrameCodec.reassembleFrame
import com.trasbd.ringbridge.ble.FrameCodec.rxMutex
import com.trasbd.ringbridge.healthconnect.HealthConnectWriter
import com.trasbd.ringbridge.protocol.BleTime
import com.trasbd.ringbridge.protocol.HealthSession
import com.trasbd.ringbridge.protocol.PowerStats
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.withLock
import java.time.Instant
import java.util.UUID


class RingClient(
    private val context: Context,
    private val mac: String,
    private val healthWriter: HealthConnectWriter,
    private val logger: ILogger,
    private val scope: CoroutineScope
) {

    companion object {
        const val OFFSET_2000 = 946_684_800 // seconds

        fun cmd(group: Int, type: Int): Int {
            require(group in 0..0xFF)
            require(type in 0..0xFF)
            return (group shl 8) or type
        }

        fun decodeCmd(cmd: Int): Pair<Int, Int> {
            val group = (cmd shr 8) and 0xFF
            val type = cmd and 0xFF
            return group to type
        }

    }

    // ----- Connected -----
    private val _isConnected = MutableStateFlow(false)
    val isConnected = _isConnected.asStateFlow()

    // ----- Ready -----
    private val _isReady = MutableStateFlow(false)
    val isReady = _isReady.asStateFlow()


    private var healthSession: HealthSession? = null

    private var powerStats = PowerStats(context, logger)
    val batteryLevel
        get() = powerStats.batteryLevel

    val chargeDateTime
        get() = powerStats.chargeDateTime

    private lateinit var bluetoothGatt: BluetoothGatt
    private var cmdAck: Boolean = false


    private lateinit var be94WriteChar: BluetoothGattCharacteristic

    private val sendQueue = ArrayDeque<PendingCommand>()

    // Tracking for background sync
    private var syncCompletionListener: (() -> Unit)? = null

    fun setSyncCompletionListener(listener: (() -> Unit)?) {
        syncCompletionListener = listener
    }

    private val gattCallback = object : BluetoothGattCallback() {
        private val cccdQueue = ArrayDeque<Pair<BluetoothGattDescriptor, ByteArray>>()
        private var cccdWriting = false

        @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
        override fun onConnectionStateChange(
            gatt: BluetoothGatt, status: Int, newState: Int
        ) {
            logger.d(
                "RingBridge", "onConnectionStateChange status=$status newState=$newState"
            )

            if (newState == BluetoothProfile.STATE_CONNECTED) {
                logger.i("RingBridge", "✅ Connected, discovering services")
                _isConnected.value = true
                gatt.discoverServices()
            } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                _isConnected.value = false
                _isReady.value = false
                logger.i("RingBridge", "❌ Disconnected")

            }
        }


        @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
        override fun onServicesDiscovered(gatt: BluetoothGatt, status: Int) {

            for (service in gatt.services) {
                if (service.uuid == RingUuids.UUID_BE94_SERVICE) {
                    for (ch in service.characteristics) {
                        if (ch.uuid == RingUuids.UUID_BE94_WRITE) {
                            be94WriteChar = ch
                            logger.i("RingBridge", "✅ BE94 write characteristic ready")

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
                logger.e("RingBridge", "❌ CCCD not found")
            }
        }


        @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
        private fun writeNextCccd() {
            val (d, v) = cccdQueue.removeFirstOrNull() ?: run {
                cccdWriting = false
                logger.i("RingBridge", "✅ All CCCDs written")
                _isReady.value = true
                requestBatteryData()
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
                logger.e("RingBridge", "❌ CCCD write failed: $status")
            }
        }

        @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
        override fun onCharacteristicChanged(
            gatt: BluetoothGatt, characteristic: BluetoothGattCharacteristic, value: ByteArray
        ) {
            logger.d(
                "RingBridge", "NOTIFY ${characteristic.uuid}: ${
                value.joinToString(" ") { "%02X".format(it) }
            }")

            // Fire-and-forget coroutine (matches asyncio.create_task)
            scope.launch {
                rxMutex.withLock {
                    if (value.isEmpty() || value.size < 4) return@withLock

                    val full = reassembleFrame(value) ?: return@withLock
                    val frame = FrameCodec.decodeFrame(full)
                    logger.d(
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

    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    private fun handleFrame(group: Int, subtype: Int, payload: ByteArray) {
        var popped = false
        val cmd = cmd(group, subtype)
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

            3 -> {
                handleGroup3(subtype, payload)
                popped = cmdAck
            }

            HealthSession.HEALTH_GROUP -> {
                handleHealth(group, subtype, payload)
                if (subtype == HealthSession.END_SUBTYPE && cmdAck) {
                    popped = true
                }
                if (cmd in 1301..<1400) popped = true
            }

            6 -> {
                handleGroup6(subtype, payload)
            }


        }

        if (popped && sendQueue.count() > 0) {
            sendPending(sendQueue[0])
        }


    }

    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    private fun handleGroup6(subtype: Int, payload: ByteArray) {
        when (subtype) {
            HealthSession.HR_LIVE_TYPE -> {
                handleHealth(HealthSession.HR_LIVE_GROUP, subtype, payload)
            }

            else -> throw NotImplementedError("Group 6 subtype")
        }
    }

    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    private fun handleGroup3(subtype: Int, payload: ByteArray) {
        when (subtype) {
            HealthSession.HR_LIVE_CMD_TYPE -> {
                handleHealth(HealthSession.HR_LIVE_CMD_GROUP, subtype, payload)
            }

            else -> throw NotImplementedError("Group 3 subtype")
        }
    }


    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    fun disconnect() {
        if (::bluetoothGatt.isInitialized) {
            bluetoothGatt.disconnect()
            bluetoothGatt.close()
            _isConnected.value = false
            _isReady.value = false
            logger.i("RingBridge", "Disconnected and closed GATT")
        }
    }

    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    fun connect() {
        logger.d("RingBridge", "startBle() called")

        val bluetoothManager = context.getSystemService(BLUETOOTH_SERVICE) as BluetoothManager

        val adapter = bluetoothManager.adapter
        if (adapter == null || !adapter.isEnabled) {
            logger.e("RingBridge", "Bluetooth adapter not available or disabled")
            return
        }

        val device = adapter.getRemoteDevice(mac)
        logger.i("RingBridge", "Connecting to $mac")

        if (Build.VERSION.SDK_INT >= 37) {
            val settings = BluetoothGattConnectionSettings.Builder()
                .setAutoConnectEnabled(false)
                .setTransport(BluetoothDevice.TRANSPORT_LE)
                .build()
            bluetoothGatt = device.connectGatt(settings, context.mainExecutor, gattCallback)!!
        } else {
            @Suppress("DEPRECATION")
            bluetoothGatt = device.connectGatt(
                context, false,
                gattCallback, BluetoothDevice.TRANSPORT_LE
            )
        }
    }

    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    fun requestBatteryData() {
        sendCmd(cmd(PowerStats.POWER_GROUP, PowerStats.POWER_TYPE))
    }

    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    fun startLiveHRSession() {
        sendCmd(
            cmd(HealthSession.HR_LIVE_CMD_GROUP, HealthSession.HR_LIVE_CMD_TYPE),
            HealthSession.HR_LIVE_START_PAYLOAD
        )
    }

    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    fun stopLiveHRSession() {
        sendCmd(
            cmd(HealthSession.HR_LIVE_CMD_GROUP, HealthSession.HR_LIVE_CMD_TYPE),
            HealthSession.HR_LIVE_STOP_PAYLOAD
        )
    }

    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    fun setTime(time: Instant = Instant.now())
    {
        sendCmd(BleTime.SET_TIME_CMD, BleTime.encode(time))
    }

    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    fun setHRInterval(interval: Int = 30) {
        sendCmd(cmd(HealthSession.HR_INTERVAL_GROUP, HealthSession.HR_INTERVAL_TYPE), byteArrayOf(1,interval.toByte()))
    }

    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    fun requestHealthData() {
        sendCmd(cmd(HealthSession.HEALTH_GROUP, HealthSession.ALL_HEALTH_TYPE))
    }

    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    fun requestSleepData() {
        sendCmd(cmd(HealthSession.HEALTH_GROUP, HealthSession.SLEEP_HEALTH_TYPE))
    }


    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    fun sendCmd(cmd: Int, payload: ByteArray = byteArrayOf()) {
        if (!isReady.value) {
            logger.e("RingBridge", "❌ Not ready yet")
            return
        }
        val pending = PendingCommand(cmd, cmd shr 8 and 0xFF, cmd and 0xFF, payload)
        sendQueue.add(pending)
        if (sendQueue.count() == 1) {
            sendPending(pending)
        }
    }

    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    private fun sendPending(pending: PendingCommand) {
        if (!::bluetoothGatt.isInitialized) {
            logger.e("RingBridge", "Cannot send command: bluetoothGatt not initialized")
            return
        }
        val frame = buildBe94Frame(pending.cmd, pending.payload)
        bluetoothGatt.writeCharacteristic(
            be94WriteChar, frame, BluetoothGattCharacteristic.WRITE_TYPE_DEFAULT
        )
        cmdAck = false
        logger.d(
            "RingBridge", "Sent ${pending.cmd} " + frame.joinToString(" ") { "%02X".format(it) })

    }

    private fun handleGroup1(subtype: Int, @Suppress("unused") payload: ByteArray) {
        when (subtype) {
            BleTime.SET_TIME_TYPE -> {
                logger.i("RingBridge", "✅ Time set")
            }
            HealthSession.HR_INTERVAL_TYPE -> {
                logger.i("RingBridge", "✅ HR interval set")
            }

            else -> TODO("Group 1 Type $subtype not handled")
        }

    }

    private fun handleGroup2(subtype: Int, payload: ByteArray) {
        when (subtype) {
            PowerStats.POWER_TYPE -> {
                powerStats.ingest(payload)
            }

            else -> throw NotImplementedError("Group 2 Type $subtype not handled")
        }
    }

    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    private fun handleHealth(group: Int, subtype: Int, payload: ByteArray) {
        val session = healthSession ?: HealthSession(logger).also {
            healthSession = it
        }

        session.ingest(group, subtype, payload)

        if (session.complete) {
            healthSession = null
            val data = session.parse()
            logger.d("RingBridge", data.toString())
            scope.launch {
                if (healthWriter.write(data)) {
                    when (data) {
                        is HealthSession.SleepResult -> sendCmd(
                            HealthSession.DELETE_SLEEP_CMD, HealthSession.DELETE_PAYLOAD
                        )

                        is HealthSession.HealthHistoryResult -> HealthSession.DELETE_HEALTH_CMD.forEach {
                            sendCmd(
                                it, HealthSession.DELETE_PAYLOAD
                            )
                        }
                    }
                }
                syncCompletionListener?.invoke()
            }


        }
    }

}

