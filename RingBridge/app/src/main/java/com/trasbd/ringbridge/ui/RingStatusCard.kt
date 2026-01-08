package com.trasbd.ringbridge.ui

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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import com.trasbd.lib.permission.PermissionModel
import java.time.LocalDateTime

@Composable
fun RingStatusCard(
    isConnected: Boolean,
    isReady: Boolean,
    batteryLevel: Int?,
    chargeDateTime: LocalDateTime?,
    onConnect: () -> Unit,
    onRequestBattery: () -> Unit,
    onRequestHealth: () -> Unit,
    onRequestSleep: () -> Unit,
    blePermissionState: PermissionModel.PermissionState
) {
    /* ---------------- Ring Connection ---------------- */

    Card {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(text = "Ring Status", style = MaterialTheme.typography.titleMedium)

            StatusRow(
                text = if (!isConnected) "Not Connected"  else if(!isReady) "Connecting..." else "Ring Connected",
                icon = if (!isConnected) "🔴" else if(!isReady) "\uD83D\uDFE1" else "🟢"
            )


            /* ---------------- Ring Battery Status ---------------- */
            if (isConnected && (batteryLevel!= null)) {

                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        // Pick an icon based on battery level
                        val batteryIcon = when {
                            batteryLevel > 80 -> "🔋"
                            batteryLevel > 20 -> "🪫"
                            else -> "⚠️"
                        }
                        Text(text = batteryIcon)
                        Text(
                            text = "Battery: $batteryLevel%",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                    // Visual progress bar for the battery
                    LinearProgressIndicator(
                        progress = { batteryLevel / 100f },
                        modifier = Modifier.fillMaxWidth().height(8.dp),
                        color = if (batteryLevel > 20) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error,
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
                    onClick = onConnect,
                    enabled = blePermissionState == PermissionModel.PermissionState.GRANTED && !isConnected,
                    modifier = Modifier.weight(1f) // Makes button fill half the row
                ) {
                    Text("Connect")
                }

                Button(
                    onClick = onRequestBattery,
                    enabled = isReady && blePermissionState == PermissionModel.PermissionState.GRANTED,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Battery Data")
                }
                // Empty space or another button to keep the grid look
                //Spacer(modifier = Modifier.weight(1f))

            }

            // You can add another Row here if you want more buttons below those
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = onRequestSleep,
                    enabled = isReady && blePermissionState == PermissionModel.PermissionState.GRANTED,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Sleep Data")
                }
                Button(
                    onClick = onRequestHealth,
                    enabled = isReady && blePermissionState == PermissionModel.PermissionState.GRANTED,
                    modifier = Modifier.weight(1f) // Makes button fill other half
                ) {
                    Text("Health Data")
                }

            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            )
            {
                Button(
                    onClick = onRequestSleep,
                    enabled = false,
                    //enabled = isReady && blePermissionState == PermissionModel.PermissionState.GRANTED,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Start HR")
                }
                Button(
                    onClick = onRequestHealth,
                    enabled = false,
                    //enabled = isReady && blePermissionState == PermissionModel.PermissionState.GRANTED,
                    modifier = Modifier.weight(1f) // Makes button fill other half
                ) {
                    Text("Stop HR")
                }

            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            )
            {
                Button(
                    onClick = onRequestSleep,
                    enabled = false,
                    //enabled = isReady && blePermissionState == PermissionModel.PermissionState.GRANTED,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Set Time")
                }
                Button(
                    onClick = onRequestHealth,
                    enabled = false,
                    //enabled = isReady && blePermissionState == PermissionModel.PermissionState.GRANTED,
                    modifier = Modifier.weight(1f).alpha(0f) // Makes button fill other half
                ) {
                    Text("")
                }

            }
        }
    }
}
