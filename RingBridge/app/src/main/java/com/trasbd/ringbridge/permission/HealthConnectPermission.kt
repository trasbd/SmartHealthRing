package com.trasbd.ringbridge.permission

import android.content.Context
import androidx.activity.result.ActivityResultLauncher
import androidx.health.connect.client.HealthConnectClient
import androidx.health.connect.client.permission.HealthPermission
import androidx.health.connect.client.records.HeartRateRecord
import androidx.health.connect.client.records.HeartRateVariabilityRmssdRecord
import androidx.health.connect.client.records.OxygenSaturationRecord
import androidx.health.connect.client.records.RespiratoryRateRecord
import androidx.health.connect.client.records.SleepSessionRecord
import com.trasbd.lib.ILogger
import com.trasbd.lib.permission.HealthConnectPermissionModel

class HealthConnectPermission(
    context: Context,
    client: HealthConnectClient,
    permissionLauncher: ActivityResultLauncher<Set<String>>,
    logger: ILogger
) : HealthConnectPermissionModel(
    context, setOf(
        // Steps
//        HealthPermission.getReadPermission(StepsRecord::class),
//        HealthPermission.getWritePermission(StepsRecord::class),

        // Heart rate
//        HealthPermission.getReadPermission(HeartRateRecord::class),
        HealthPermission.getWritePermission(HeartRateRecord::class),

        // Sleep
//        HealthPermission.getReadPermission(SleepSessionRecord::class),
        HealthPermission.getWritePermission(SleepSessionRecord::class),

        // Blood oxygen / SpO₂
//        HealthPermission.getReadPermission(OxygenSaturationRecord::class),
        HealthPermission.getWritePermission(OxygenSaturationRecord::class),

//        HealthPermission.getReadPermission(HeartRateVariabilityRmssdRecord::class),
        HealthPermission.getWritePermission(HeartRateVariabilityRmssdRecord::class),

//        HealthPermission.getReadPermission(RespiratoryRateRecord::class),
        HealthPermission.getWritePermission(RespiratoryRateRecord::class),

        ), permissionLauncher, client, logger
)