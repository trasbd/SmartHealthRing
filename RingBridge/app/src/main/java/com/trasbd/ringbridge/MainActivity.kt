package com.trasbd.ringbridge

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresPermission
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.health.connect.client.HealthConnectClient
import androidx.health.connect.client.PermissionController
import androidx.lifecycle.lifecycleScope
import com.trasbd.lib.permission.PermissionModel
import com.trasbd.lib.uiLogger.UiLogger
import com.trasbd.lib.utilities.loggingCoroutineExceptionHandler
import com.trasbd.ringbridge.ble.RingClient
import com.trasbd.ringbridge.healthconnect.HealthConnectWriter
import com.trasbd.ringbridge.permission.BluetoothPermission
import com.trasbd.ringbridge.permission.HealthConnectPermission
import com.trasbd.ringbridge.ui.MainScreen
import com.trasbd.ringbridge.ui.model.PermissionUiModel
import com.trasbd.ringbridge.ui.model.RingUiModel
import com.trasbd.ringbridge.ui.theme.RingBridgeTheme
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import java.time.Instant

class MainActivity : ComponentActivity() {
    @Suppress("PropertyName")
    val RING_MAC = "07:35:00:01:8A:EC"
    val logger = UiLogger()

    private val coroutineExceptionHandler = loggingCoroutineExceptionHandler(logger, "RingBridge")


    // Initialize these properly to avoid crashes
    private lateinit var healthWriter: HealthConnectWriter
    private lateinit var ring: RingClient // Don't init here yet

    private lateinit var blePermissions: BluetoothPermission
    private lateinit var healthConnectPermissions: HealthConnectPermission

    // Use Compose state for the permission models
    private var bleState = mutableStateOf(PermissionModel.PermissionState.UNKNOWN)
    private var hcState = mutableStateOf(PermissionModel.PermissionState.UNKNOWN)

    @SuppressLint("MissingPermission")
    private val bleLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            lifecycleScope.launch(coroutineExceptionHandler) {
                bleState.value = blePermissions.update()
                if (bleState.value == PermissionModel.PermissionState.GRANTED) {
                    ring.connect()
                }
            }
        }

    private val healthConnectLauncher =
        registerForActivityResult(PermissionController.createRequestPermissionResultContract()) {
            lifecycleScope.launch(coroutineExceptionHandler) {
                hcState.value = healthConnectPermissions.update()
            }
        }


    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Initialize dependencies in order
        val healthConnectClient = HealthConnectClient.getOrCreate(this)
        val ringScope = lifecycleScope + coroutineExceptionHandler

        healthWriter = HealthConnectWriter(healthConnectClient, logger)
        ring = RingClient(this, RING_MAC, healthWriter, logger, ringScope)

        blePermissions = BluetoothPermission(this, bleLauncher, logger)
        healthConnectPermissions =
            HealthConnectPermission(this, healthConnectClient, healthConnectLauncher, logger)

        if (blePermissions.permissionState != PermissionModel.PermissionState.GRANTED) {
            lifecycleScope.launch(coroutineExceptionHandler) { blePermissions.request() }
        }



        enableEdgeToEdge()
        setContent {
            RingBridgeTheme {
                // 2. COLLECT the flows from RingClient here
                val isConnected by ring.isConnected.collectAsState()
                val isReady by ring.isReady.collectAsState()
                val batteryLevel by ring.batteryLevel.collectAsState()
                val chargeDate by ring.chargeDateTime.collectAsState()

                val blePermission = PermissionUiModel(
                    bleState.value
                ) { lifecycleScope.launch(coroutineExceptionHandler) { blePermissions.request() } }
                val hcPermission = PermissionUiModel(
                    hcState.value
                ) { lifecycleScope.launch(coroutineExceptionHandler) { healthConnectPermissions.request() } }
                val ringUi = RingUiModel(
                    isConnected,
                    isReady,
                    batteryLevel,
                    chargeDate,
                    { ring.connect() },
                    { ring.requestBatteryData() },
                    { ring.requestHealthData() },
                    { ring.requestSleepData() },
                    { ring.startLiveHRSession() },
                    { ring.stopLiveHRSession() },
                    onSetTime = { ring.setTime(Instant.now()) },
                    onTest = { ring.setHRInterval(10) }
                )


                @SuppressLint("MissingPermission") MainScreen(
                    blePermission, hcPermission, ringUi, logger = logger
                )
            }
        }
    }

    // Update permission states when returning to app
    override fun onResume() {
        super.onResume()
        lifecycleScope.launch(coroutineExceptionHandler) {
            bleState.value = blePermissions.update()
            hcState.value = healthConnectPermissions.update()
        }
    }
}

