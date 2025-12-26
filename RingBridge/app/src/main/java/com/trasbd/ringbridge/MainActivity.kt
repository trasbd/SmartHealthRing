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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.trasbd.ringbridge.ui.theme.RingBridgeTheme
import java.util.UUID

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
                gatt.discoverServices()
            } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                Log.d("RingBridge", "❌ Disconnected")
            }
        }


        @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
        override fun onServicesDiscovered(gatt: BluetoothGatt, status: Int) {

            for (service in gatt.services) {
                for (ch in service.characteristics) {

                    if (ch.uuid !in NOTIFY_ALLOWLIST) {
                        continue
                    }

                    val props = ch.properties
                    val canNotify =
                        props and BluetoothGattCharacteristic.PROPERTY_NOTIFY != 0
                    val canIndicate =
                        props and BluetoothGattCharacteristic.PROPERTY_INDICATE != 0

                    if (canNotify || canIndicate) {
                        enableNotifications(gatt, ch)
                    }
                }
            }
        }

        @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
        private fun enableNotifications(
            gatt: BluetoothGatt,
            characteristic: BluetoothGattCharacteristic
        ) {
            gatt.setCharacteristicNotification(characteristic, true)

            val cccd = characteristic.getDescriptor(
                UUID.fromString("00002902-0000-1000-8000-00805f9b34fb")
            )

            if (cccd != null) {
                cccd.value = BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE
                gatt.writeDescriptor(cccd)
                Log.d("RingBridge", "📡 Notifications enabled")
            } else {
                Log.e("RingBridge", "❌ CCCD not found")
            }
        }


        @Deprecated("Deprecated in Java")
        override fun onCharacteristicChanged(
            gatt: BluetoothGatt,
            characteristic: BluetoothGattCharacteristic
        ) {
            val data = characteristic.value
            // THIS == your _on_notify()
        }
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
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "RingBridge",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
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

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RingBridgeTheme {
        Greeting("RingBridge")
    }
}