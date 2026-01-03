package com.trasbd.ringbridge.healthconnect

import androidx.health.connect.client.HealthConnectClient
import androidx.health.connect.client.records.HeartRateRecord
import androidx.health.connect.client.records.HeartRateVariabilityRmssdRecord
import androidx.health.connect.client.records.OxygenSaturationRecord
import androidx.health.connect.client.records.Record
import androidx.health.connect.client.records.RespiratoryRateRecord
import androidx.health.connect.client.records.SleepSessionRecord
import androidx.health.connect.client.records.metadata.Device
import androidx.health.connect.client.records.metadata.Device.Companion.TYPE_RING
import androidx.health.connect.client.records.metadata.Metadata
import androidx.health.connect.client.units.Percentage
import com.trasbd.ringbridge.protocol.HealthSession
import com.trasbd.lib.uiLogger.UiLogger
import java.time.Instant

class HealthConnectWriter(
    private val client: HealthConnectClient,
    private val logger: UiLogger
) {
    suspend fun write(data: Any): Boolean {
        return when (data) {
            is HealthSession.SleepResult -> writeSleep(data)
            is HealthSession.HealthHistoryResult -> writeHealth(data)
            else -> false
        }
    }

    private suspend fun writeHealth(data: HealthSession.HealthHistoryResult): Boolean { val records = mutableListOf<Record>()

        data.data.forEach { session ->
            val start = Instant.ofEpochMilli(session.startTime)
            val end = Instant.ofEpochMilli(session.startTime + 1)

            if (end < Instant.now()) {

                val samples = mutableListOf(
                    HeartRateRecord.Sample(
                        start, session.heartValue.toLong()
                    )
                )

                val meta = Metadata.autoRecorded(Device(TYPE_RING))

                records.add(
                    OxygenSaturationRecord(
                        start, null, Percentage(session.ooValue.toDouble()), meta
                    )
                )
                records.add(HeartRateRecord(start, null, end, null, samples, meta))
                records.add(
                    HeartRateVariabilityRmssdRecord(
                        start, null, session.hrvValue.toDouble(), meta
                    )
                )
                records.add(
                    RespiratoryRateRecord(
                        start, null, session.respiratoryRateValue.toDouble(), meta
                    )
                )
            }
        }

        if (records.isEmpty()) {
            logger.log("RingBridge", "⚠️ No records to insert")
            return false
        }

        return postToHealthConnect(records)
    }
    private suspend fun writeSleep(data: HealthSession.SleepResult): Boolean {
        val sessions = mutableListOf<SleepSessionRecord>()
        data.data.forEach { it ->
            val segments = mutableListOf<SleepSessionRecord.Stage>()
            it.sleepData.forEach { iit ->
                val start = Instant.ofEpochMilli(iit.sleepStartTime)
                val end = Instant.ofEpochMilli(iit.sleepStartTime + (iit.sleepLen * 1000))
                val type = HealthSession.SLEEP_TYPES[iit.sleepType]!!
                if (start < end) {
                    val currentSegment = SleepSessionRecord.Stage(start, end, type)
                    segments.add(currentSegment)
                }
            }

            val start = Instant.ofEpochMilli(it.startTime)
            val end = Instant.ofEpochMilli(it.endTime)
            val meta = Metadata.autoRecorded(Device(TYPE_RING))
            sessions.add(SleepSessionRecord(start, null, end, null, meta, null, null, segments))

        }


        if (sessions.isEmpty()) {
            logger.log("RingBridge", "⚠️ No records to insert")
            return false
        }


        return postToHealthConnect(sessions)
    }
    private suspend fun postToHealthConnect(records: List<Record>): Boolean {
        try {
            client.insertRecords(records)
            logger.log("RingBridge", "✅ Posted ${records.size} records")


        } catch (e: Exception) {
            logger.log("RingBridge", "❌ Health Connect insert failed: ${e.message}")
            e.printStackTrace()
            return false
        }
        return true

    }
}