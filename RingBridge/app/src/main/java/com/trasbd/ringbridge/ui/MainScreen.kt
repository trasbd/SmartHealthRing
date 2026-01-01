package com.trasbd.ringbridge.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.trasbd.ringbridge.MainActivity
import com.trasbd.ringbridge.ui.uiLogger.LogConsole
import com.trasbd.ringbridge.ui.uiLogger.UiLogger

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
    logger: UiLogger
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