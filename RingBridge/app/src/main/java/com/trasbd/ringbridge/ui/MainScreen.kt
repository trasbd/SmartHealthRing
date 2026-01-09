package com.trasbd.ringbridge.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.trasbd.lib.uiLogger.LogConsole
import com.trasbd.lib.uiLogger.UiLogger
import com.trasbd.ringbridge.ui.card.PermissionCard
import com.trasbd.ringbridge.ui.card.RingStatusCard
import com.trasbd.ringbridge.ui.model.PermissionUiModel
import com.trasbd.ringbridge.ui.model.RingUiModel

@Composable
fun MainScreen(

    blePermission: PermissionUiModel,
    healthConnectPermission: PermissionUiModel,
    ringUiModel: RingUiModel,

    logger: UiLogger
) {
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text("Ring Bridge", style = MaterialTheme.typography.headlineMedium)

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        PermissionCard(
                            "Bluetooth Permission",
                            blePermission,
                            Modifier.weight(1f)
                        )
                        PermissionCard(
                            "HealthConnect Permissions",
                            healthConnectPermission,
                            Modifier.weight(1f)
                        )
                    }
                    Row {
                        RingStatusCard(
                            ringUiModel,
                            blePermission.permissionState
                        )
                    }
                }

            }

            Card {
                LogConsole(
                    logs = logger.lines, modifier = Modifier
                        .fillMaxWidth()
                        .height(170.dp)
                )
            }
        }
    }

}