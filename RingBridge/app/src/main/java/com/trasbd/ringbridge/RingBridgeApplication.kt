package com.trasbd.ringbridge

import android.app.Application
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.trasbd.ringbridge.worker.SyncWorker
import java.util.concurrent.TimeUnit

class RingBridgeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        schedulePeriodicSync()
    }

    private fun schedulePeriodicSync() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
            .setRequiresBatteryNotLow(true)
            .build()

        val syncRequest = PeriodicWorkRequestBuilder<SyncWorker>(
            Constants.SYNC_INTERVAL_MINUTES, TimeUnit.MINUTES
        )
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "RingSyncWork",
            ExistingPeriodicWorkPolicy.KEEP,
            syncRequest
        )
    }
}
