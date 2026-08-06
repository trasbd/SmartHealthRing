package com.trasbd.ringbridge.ui.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import com.trasbd.lib.permission.PermissionModel
import com.trasbd.ringbridge.ui.StatusRow
import com.trasbd.ringbridge.ui.model.RingUiModel

@Composable
fun RingStatusCard(
    ring: RingUiModel,
    blePermissionState: PermissionModel.PermissionState,
    modifier: Modifier = Modifier

) {
    Card {
        Column(
            modifier = modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(text = "Ring Status", style = MaterialTheme.typography.titleMedium)

            StatusRow(
                text = if (!ring.isConnected) "Not Connected" else if (!ring.isReady) "Connecting..." else "Ring Connected",
                icon = if (!ring.isConnected) "🔴" else if (!ring.isReady) "\uD83D\uDFE1" else "🟢"
            )


            /* ---------------- Ring Battery Status ---------------- */
            if (ring.isConnected && (ring.batteryLevel != null)) {

                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        // Pick an icon based on battery level
                        val batteryIcon = when {
                            ring.batteryLevel > 80 -> "🔋"
                            ring.batteryLevel > 20 -> "🪫"
                            else -> "⚠️"
                        }
                        Text(text = batteryIcon)
                        Text(
                            text = "Battery: ${ring.batteryLevel}%",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                    // Visual progress bar for the battery
                    LinearProgressIndicator(
                        progress = { ring.batteryLevel / 100f },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp),
                        color = if (ring.batteryLevel > 20) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error,
                        trackColor = MaterialTheme.colorScheme.surfaceVariant,
                        strokeCap = StrokeCap.Round,
                    )
                }
            }


            // This Row creates your "Grid" row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = ring.onConnect,
                    enabled = blePermissionState == PermissionModel.PermissionState.GRANTED && !ring.isConnected,
                    modifier = Modifier.weight(1f) // Makes button fill half the row
                ) {
                    Text("Connect")
                }

                Button(
                    onClick = ring.onRequestBattery,
                    enabled = ring.isReady && blePermissionState == PermissionModel.PermissionState.GRANTED,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Battery Data")
                }


            }

            // You can add another Row here if you want more buttons below those
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = ring.onRequestSleep,
                    enabled = ring.isReady && blePermissionState == PermissionModel.PermissionState.GRANTED,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Sleep Data")
                }
                Button(
                    onClick = ring.onRequestHealth,
                    enabled = ring.isReady && blePermissionState == PermissionModel.PermissionState.GRANTED,
                    modifier = Modifier.weight(1f) // Makes button fill other half
                ) {
                    Text("Health Data")
                }

            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = ring.onLiveHRStart,
                    enabled = ring.isReady && blePermissionState == PermissionModel.PermissionState.GRANTED,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Start HR")
                }
                Button(
                    onClick = ring.onLiveHRStop,
                    enabled = ring.isReady && blePermissionState == PermissionModel.PermissionState.GRANTED,
                    modifier = Modifier.weight(1f) // Makes button fill other half
                ) {
                    Text("Stop HR")
                }

            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = ring.onSetTime,
                    enabled = ring.isReady && blePermissionState == PermissionModel.PermissionState.GRANTED,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Set Time")
                }
                // Empty space or another button to keep the grid look
                //Spacer(modifier = Modifier.weight(1f))
                Button(
                    onClick = ring.onTest,
                    enabled = ring.isReady && blePermissionState == PermissionModel.PermissionState.GRANTED,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Test")
                }

            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = ring.onOpenLog,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Open Log File")
                }
            }
        }
    }
}
