package com.trasbd.ringbridge.ui.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.trasbd.lib.permission.PermissionModel
import com.trasbd.ringbridge.ui.model.PermissionUiModel

@Composable
fun PermissionCard(
    title: String,
    permissionModel: PermissionUiModel,
    modifier: Modifier
) {
    Card(modifier) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(title, style = MaterialTheme.typography.titleMedium)
            Button(
                enabled = permissionModel.permissionState != PermissionModel.PermissionState.GRANTED,
                onClick = permissionModel.onRequestPermission
            ) {
                Text(
                    if (permissionModel.permissionState == PermissionModel.PermissionState.GRANTED) "Granted"
                    else "Grant Permission"
                )
            }
        }
    }
}
