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
import com.trasbd.lib.permission.PermissionModel
import com.trasbd.lib.uiLogger.LogConsole
import com.trasbd.lib.uiLogger.UiLogger

@Composable
fun MainScreen(
    blePermissionState: PermissionModel.PermissionState,
    healthConnectPermissionState: PermissionModel.PermissionState,
    isConnected: Boolean,
    isReady: Boolean,
    onRequestHealthConnectPermission: () -> Unit,
    onRequestBLEPermission: () -> Unit,
    onConnect: () -> Unit,
    onRequestHealth: () -> Unit,
    onRequestSleep: () -> Unit,
    logger: UiLogger
)
{
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
                        PermissionModel.PermissionState.GRANTED -> {
                            StatusRow("Bluetooth access granted", "✅")
                        }

                        PermissionModel.PermissionState.DENIED,
                        PermissionModel.PermissionState.PARTIAL -> {
                            StatusRow("Bluetooth permission required", "⚠️")
                            Text(
                                text = "Please allow Bluetooth access when prompted.",
                                style = MaterialTheme.typography.bodySmall
                            )
                        }

                        PermissionModel.PermissionState.PERMANENTLY_DENIED -> {
                            StatusRow("Bluetooth permission denied", "❌")
                            Text(
                                text = "Enable Bluetooth permission in system settings.",
                                style = MaterialTheme.typography.bodySmall
                            )
                            Button(
                                onClick = onRequestBLEPermission,
                                modifier = Modifier.padding(top = 8.dp)
                            ) {
                                Text("Open App Settings")
                            }
                        }

                        PermissionModel.PermissionState.UNKNOWN -> {
                            StatusRow("Bluetooth permission unknown", "❓")
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

                    Button(
                        onClick = onRequestHealthConnectPermission,
                        enabled = healthConnectPermissionState != PermissionModel.PermissionState.GRANTED
                    ) {
                        Text(
                            if (healthConnectPermissionState == PermissionModel.PermissionState.GRANTED)
                                "Health Connect Granted"
                            else
                                "Grant Health Connect Permissions"
                        )
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
                        enabled = blePermissionState == PermissionModel.PermissionState.GRANTED && !isConnected
                    ) {
                        Text("Connect to Ring")
                    }

                    Button(
                        onClick = onRequestHealth,
                        enabled = isReady && blePermissionState == PermissionModel.PermissionState.GRANTED
                    ) {
                        Text("Get Health Data")
                    }

                    Button(
                        onClick = onRequestSleep,
                        enabled = isReady && blePermissionState == PermissionModel.PermissionState.GRANTED
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