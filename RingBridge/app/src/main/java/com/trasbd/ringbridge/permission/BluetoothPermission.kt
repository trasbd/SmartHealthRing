package com.trasbd.ringbridge.permission

import android.Manifest
import android.content.Context
import androidx.activity.result.ActivityResultLauncher
import com.trasbd.lib.ILogger
import com.trasbd.lib.permission.BluetoothPermissionModel

class BluetoothPermission(
    context: Context, permissionLauncher: ActivityResultLauncher<Array<String>>, logger: ILogger
) : BluetoothPermissionModel(
    context, arrayOf(
        Manifest.permission.BLUETOOTH_CONNECT, Manifest.permission.BLUETOOTH_SCAN
    ), permissionLauncher, logger
)
