package com.trasbd.ringbridge.worker

import android.annotation.SuppressLint
import android.content.Context
import androidx.health.connect.client.HealthConnectClient
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.trasbd.lib.FileLogger
import com.trasbd.ringbridge.Constants
import com.trasbd.ringbridge.ble.RingClient
import com.trasbd.ringbridge.healthconnect.HealthConnectWriter
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.withTimeoutOrNull
import kotlin.time.Duration.Companion.milliseconds

class SyncWorker(context: Context, params: WorkerParameters) :
    CoroutineWorker(context, params) {

    @SuppressLint("MissingPermission")
    override suspend fun doWork(): Result {
        val logger = FileLogger(applicationContext, Constants.SYNC_LOG_FILE)
        logger.i("SyncWorker", "Starting background sync")

        val healthConnectClient = HealthConnectClient.getOrCreate(applicationContext)
        val healthWriter = HealthConnectWriter(healthConnectClient, logger)
        
        val syncJob = CompletableDeferred<Unit>()
        val ring = RingClient(
            applicationContext,
            Constants.RING_MAC,
            healthWriter,
            logger,
            MainScope() // Or a more appropriate scope
        )

        ring.setSyncCompletionListener {
            logger.i("SyncWorker", "Sync complete notification received")
            syncJob.complete(Unit)
        }

        try {
            ring.connect()
            
            // Wait for connection and readiness
            val isReady = withTimeoutOrNull(30_000.milliseconds) {
                while (!ring.isReady.value) {
                    kotlinx.coroutines.delay(500.milliseconds)
                }
                true
            } ?: false

            if (!isReady) {
                logger.e("SyncWorker", "Timed out waiting for Ring to be ready")
                ring.disconnect()
                return Result.retry()
            }

            logger.i("SyncWorker", "Ring ready, requesting data")
            
            // Log battery level if available
            val battery = withTimeoutOrNull(5000.milliseconds) {
                while (ring.batteryLevel.value == null) {
                    kotlinx.coroutines.delay(500.milliseconds)
                }
                ring.batteryLevel.value
            }
            logger.i("SyncWorker", "Current Ring Battery Level: ${battery ?: "Unknown"}%")

            ring.requestHealthData()
            ring.requestSleepData()

            // Wait for sync completion with a timeout (e.g., 5 minutes)
            val success = withTimeoutOrNull(300_000.milliseconds) {
                syncJob.await()
                true
            } ?: false

            if (!success) {
                logger.e("SyncWorker", "Timed out waiting for sync completion")
                ring.disconnect()
                return Result.retry()
            }

            logger.i("SyncWorker", "Background sync finished successfully")
            ring.disconnect()
            return Result.success()

        } catch (e: Exception) {
            logger.e("SyncWorker", "Error during background sync", e)
            ring.disconnect()
            return Result.failure()
        }
    }
}
