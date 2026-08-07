package com.trasbd.lib.permission

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.activity.result.ActivityResultLauncher
import com.trasbd.lib.uiLogger.UiLogger

open class BluetoothPermissionModel(
    context: Context,
    permissionList: Array<String>,
    permissionLauncher: ActivityResultLauncher<Array<String>>,
    logger: UiLogger
) : PermissionModel<Array<String>>(
    context, permissionList, permissionLauncher, logger
) {
    override suspend fun update(): PermissionState {
        var allGranted = true
        var partial = false
        permissionList.forEach {
            val granted = context.checkSelfPermission(it) == PackageManager.PERMISSION_GRANTED
            allGranted = allGranted && granted
            partial = partial || granted
        }
        permissionState = when {
            allGranted -> PermissionState.GRANTED
            !allGranted && partial -> PermissionState.PARTIAL
            else -> PermissionState.PERMANENTLY_DENIED
        }
        return permissionState
    }

    override suspend fun request(): PermissionState {
        when (permissionState) {
            PermissionState.UNKNOWN, PermissionState.DENIED, PermissionState.PARTIAL -> 
                permissionLauncher.launch(permissionList)

            else -> openAppSettings()
        }

        return update()
    }

    private fun openAppSettings() {
        val intent = Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.fromParts("package", context.packageName, null)
        )
        context.startActivity(intent)
    }


}
