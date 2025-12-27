package com.trasbd.ringbridge

import android.Manifest
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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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

class MainActivity : ComponentActivity() {

    private val UUID_BE94_SERVICE = UUID.fromString("be940000-7333-be46-b7ae-689e71722bd5")

    private val UUID_BE94_WRITE = UUID.fromString("be940001-7333-be46-b7ae-689e71722bd5")
    private val UUID_BE94_WRITE2 = UUID.fromString("be940002-7333-be46-b7ae-689e71722bd5")
    private val UUID_IND_BE94_SECOND = UUID.fromString("be940003-7333-be46-b7ae-689e71722bd5")

    private val UUID_NOTIFY_AE02 = UUID.fromString("0000ae02-0000-1000-8000-00805f9b34fb")
    private val UUID_IND_FEA1 = UUID.fromString("0000fea1-0000-1000-8000-00805f9b34fb")
    private val UUID_IND_FEA2 = UUID.fromString("0000fea2-0000-1000-8000-00805f9b34fb")

    private val UUID_NOTIFY_NUS_TX = UUID.fromString("6e400003-b5a3-f393-e0a9-e50e24dcca9e")

    private val NOTIFY_ALLOWLIST = setOf(
        UUID_BE94_WRITE,
        UUID_IND_BE94_SECOND,
        UUID_NOTIFY_AE02,
        UUID_IND_FEA2,
        UUID_NOTIFY_NUS_TX
    )

    private val RING_MAC = "07:35:00:01:8A:EC"

    private lateinit var be94WriteChar: BluetoothGattCharacteristic

    private var isConnected by mutableStateOf(false)
    private var isReady by mutableStateOf(false)


    // =====================
    // BLE permissions
    // =====================
    @RequiresApi(Build.VERSION_CODES.S)
    private val blePermissions = arrayOf(
        Manifest.permission.BLUETOOTH_CONNECT,
        Manifest.permission.BLUETOOTH_SCAN
    )

    private val permissionLauncher =
        registerForActivityResult(
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
            gatt: BluetoothGatt,
            status: Int,
            newState: Int
        ) {
            Log.d(
                "RingBridge",
                "onConnectionStateChange status=$status newState=$newState"
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
                if (service.uuid == UUID_BE94_SERVICE) {
                    for (ch in service.characteristics) {
                        if (ch.uuid == UUID_BE94_WRITE) {
                            be94WriteChar = ch
                            isReady = true
                            Log.d("RingBridge", "✅ BE94 write characteristic ready")
                        }
                    }
                }

                for (ch in service.characteristics) {
                    if (ch.uuid !in NOTIFY_ALLOWLIST) continue

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
            gatt: BluetoothGatt,
            characteristic: BluetoothGattCharacteristic
        ) {
            gatt.setCharacteristicNotification(characteristic, true)
            val props = characteristic.properties
            val canIndicate =
                props and BluetoothGattCharacteristic.PROPERTY_INDICATE != 0
            val cccd = characteristic.getDescriptor(
                UUID.fromString("00002902-0000-1000-8000-00805f9b34fb")
            )

            if (cccd != null) {

                val cccdValue =
                    if (canIndicate)
                        BluetoothGattDescriptor.ENABLE_INDICATION_VALUE
                    else
                        BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE

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
            gatt: BluetoothGatt,
            descriptor: BluetoothGattDescriptor,
            status: Int
        ) {
            if (status == BluetoothGatt.GATT_SUCCESS) {
                writeNextCccd()
            } else {
                Log.e("RingBridge", "❌ CCCD write failed: $status")
            }
        }


        @RequiresApi(Build.VERSION_CODES.TIRAMISU)
        override fun onCharacteristicChanged(
            gatt: BluetoothGatt,
            characteristic: BluetoothGattCharacteristic,
            value: ByteArray
        ) {
            Log.d(
                "RingBridge",
                "NOTIFY ${characteristic.uuid}: ${
                    value.joinToString(" ") { "%02X".format(it) }
                }"
            )

            // Fire-and-forget coroutine (matches asyncio.create_task)
            CoroutineScope(Dispatchers.IO).launch {
                rxMutex.withLock {
                    if (value.isEmpty() || value.size < 4) return@withLock

                    val full = reassembleFrame(value) ?: return@withLock
                    val frame = decodeFrame(full)
                    Log.d("RingBridge", "Received ${frame.group} ${frame.subtype} ${frame.payload.joinToString(" ") { "%02X".format(it) }}")
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

        val expectedLen =
            (data[2].toInt() and 0xFF) or
                    ((data[3].toInt() and 0xFF) shl 8)

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
            (rxBuffer!![2].toInt() and 0xFF) or
                    ((rxBuffer!![3].toInt() and 0xFF) shl 8)

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

    private data class DecodedFrame(
        val group: Int,
        val subtype: Int,
        val payload: ByteArray
    )

    private fun decodeFrame(data: ByteArray): DecodedFrame {
        val group = data[0].toInt() and 0xFF
        val subtype = data[1].toInt() and 0xFF

        val totalLen =
            (data[2].toInt() and 0xFF) or
                    ((data[3].toInt() and 0xFF) shl 8)

        val payloadLen = totalLen - 6
        val payload = data.copyOfRange(4, 4 + payloadLen)

        return DecodedFrame(group, subtype, payload)
    }

    private var cmdAck: Boolean = false

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    private fun handleFrame(group: Int, subtype: Int, payload: ByteArray)
    {
        val popped = false
        if(sendQueue.count()>0)
        {
            val head = sendQueue[0]
            if(head.group == group && head.subtype == subtype)
            {
                sendQueue.removeFirst()
                cmdAck = true
            }
        }

        when(group){
            1-> {handleGroup1(subtype, payload)}
            2-> {handleGroup2(subtype, payload)
                popped = true}
            5-> {
                handleGroup5(subtype, payload)
                if(subtype == HealthSession.EndSubtype && cmdAck)
                {
                    popped = true
                }
            }
        }

        if (popped && sendQueue.count() > 0)
        {
            SendPending(sendQueue[0])
        }


    }

    private fun handleGroup1(subtype: Int, payload: ByteArray)
    {

    }
    private fun handleGroup2(subtype: Int, payload: ByteArray)
    {
        
    }

    private fun handleGroup5(subtype: Int, payload: ByteArray)
    {
        
    }



    // =====================
    // Activity lifecycle
    // =====================
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            permissionLauncher.launch(blePermissions)
        }

        enableEdgeToEdge()
        setContent {
            RingBridgeTheme {
                MainScreen(
                    isConnected = isConnected,
                    isReady = isReady,
                    onRequestData = {
                        requestHealthData()
                    }
                )
            }
        }

    }

    // =====================
    // BLE start
    // =====================
    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    private fun startBle() {
        Log.d("RingBridge", "startBle() called")

        val bluetoothManager =
            getSystemService(BLUETOOTH_SERVICE) as BluetoothManager

        val adapter = bluetoothManager.adapter
        if (adapter == null || !adapter.isEnabled) {
            Log.e("RingBridge", "Bluetooth adapter not available or disabled")
            return
        }

        val device = adapter.getRemoteDevice(RING_MAC)
        Log.d("RingBridge", "Connecting to $RING_MAC")

        bluetoothGatt = device.connectGatt(
            this,
            false,              // do NOT autoConnect
            gattCallback,
            BluetoothDevice.TRANSPORT_LE
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

    private data class PendingCommand(
        val cmd: Int,
        val group: Int,
        val subtype: Int,
        val payload: ByteArray = byteArrayOf()
    )

    private val sendQueue = ArrayDeque<PendingCommand>()

    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun SendCmd(cmd: Int, payload: ByteArray = byteArrayOf())
    {
        val pending = PendingCommand(cmd, cmd shr 8 and 0xFF, cmd and 0xFF, payload)
        sendQueue.add(pending)
        if(sendQueue.count() == 1)
        {
            SendPending(pending)
        }
    }

    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun SendPending(pending: PendingCommand) {
        val frame = buildBe94Frame(pending.cmd, pending.payload)
        val ret = bluetoothGatt.writeCharacteristic(be94WriteChar, frame, BluetoothGattCharacteristic.WRITE_TYPE_DEFAULT)
        Log.d("RingBridge", "Sent ${pending.cmd} "+
                frame.joinToString(" ") { "%02X".format(it) })

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

    class HealthSession(healthType: Int = null, blocks: ByteArray = byteArrayOf(), complete: Boolean = false)
    {
        val SleepHealthHeader = 4
        val HealthTypes = {SleepHealthHeader, 8, 9}
        val EndSubtype = 128
        val EndCommand = 1408
        EndPayload = byteArrayOf()
    }




}

@Composable
fun MainScreen(
    isConnected: Boolean,
    isReady: Boolean,
    onRequestData: () -> Unit
) {
    Scaffold { padding ->
        Column(
            modifier = Modifier.padding(padding),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(text = if (isConnected) "Connected" else "Not connected")

            Button(
                onClick = onRequestData,
                enabled = isReady
            ) {
                Text("Get Ring Data")
            }
        }
    }
}
