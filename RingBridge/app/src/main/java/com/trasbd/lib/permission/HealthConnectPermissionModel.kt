package com.trasbd.lib.permission

import android.content.Context
import androidx.activity.result.ActivityResultLauncher
import androidx.health.connect.client.HealthConnectClient
import com.trasbd.lib.ILogger

open class HealthConnectPermissionModel(
    context: Context,
    permissionList: Set<String>,
    permissionLauncher: ActivityResultLauncher<Set<String>>,
    private val client: HealthConnectClient,
    logger: ILogger
) : PermissionModel<Set<String>>(
    context, permissionList, permissionLauncher, logger
) {

    override suspend fun update(): PermissionState {
        val granted = client.permissionController.getGrantedPermissions()
        permissionState = if (granted.containsAll(permissionList.toList())) {
            PermissionState.GRANTED
        } else {
            PermissionState.DENIED
        }
        return permissionState
    }

    override suspend fun request(): PermissionState {
        permissionLauncher.launch(permissionList)

        return update()
    }
}