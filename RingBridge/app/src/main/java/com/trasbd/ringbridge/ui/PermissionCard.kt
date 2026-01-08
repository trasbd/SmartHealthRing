package com.trasbd.ringbridge.ui

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

@Composable
fun PermissionCard(
    title: String,
    permissionState: PermissionModel.PermissionState,
    onRequestPermission: () -> Unit,
    modifier: Modifier
) {
    Card(modifier) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(title, style = MaterialTheme.typography.titleMedium)
            Button(
                enabled = permissionState != PermissionModel.PermissionState.GRANTED,
                onClick = onRequestPermission
            ) {
                Text(
                    if (permissionState == PermissionModel.PermissionState.GRANTED) "Granted"
                    else "Grant Permission"
                )
            }
        }
    }
}
