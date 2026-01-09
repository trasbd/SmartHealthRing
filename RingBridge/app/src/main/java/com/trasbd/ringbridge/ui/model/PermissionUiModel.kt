package com.trasbd.ringbridge.ui.model

import com.trasbd.lib.permission.PermissionModel

data class PermissionUiModel(
    val permissionState: PermissionModel.PermissionState,
    val onRequestPermission: () -> Unit
)