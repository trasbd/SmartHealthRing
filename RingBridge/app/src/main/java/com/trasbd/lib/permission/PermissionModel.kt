package com.trasbd.lib.permission

import android.content.Context
import androidx.activity.result.ActivityResultLauncher
import com.trasbd.lib.ILogger

abstract class PermissionModel<TRequest>(
    protected val context: Context,
    protected val permissionList: TRequest,
    protected val permissionLauncher: ActivityResultLauncher<TRequest>,
    protected val logger: ILogger
)
{
    var permissionState: PermissionState = PermissionState.UNKNOWN


    abstract suspend fun update(): PermissionState
    abstract suspend fun request(): PermissionState

    enum class PermissionState {
        GRANTED, PARTIAL, DENIED, PERMANENTLY_DENIED, UNKNOWN
    }
}