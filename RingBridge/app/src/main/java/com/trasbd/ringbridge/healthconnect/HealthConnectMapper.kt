package com.trasbd.ringbridge.healthconnect

import androidx.health.connect.client.records.HeartRateRecord
import androidx.health.connect.client.records.metadata.Metadata
import com.trasbd.ringbridge.protocol.HealthSession
import java.time.Duration
import java.time.ZoneId

fun HealthSession.LiveHRSession.toHeartRateRecord(
    metadata: Metadata
): HeartRateRecord {

    require(heartValues.isNotEmpty()) {
        "LiveHRSession contains no heart rate samples"
    }

    val zoneId = ZoneId.systemDefault()
    val startOffset = zoneId.rules.getOffset(startTime)
    val endOffset = zoneId.rules.getOffset(endTime)

    val durationMs = Duration.between(startTime, endTime).toMillis()
    val intervalMs = durationMs / heartValues.size

    val samples = heartValues.mapIndexed { index, bpm ->
        HeartRateRecord.Sample(
            beatsPerMinute = bpm.toLong(),
            time = startTime.plusMillis(intervalMs * index)
        )
    }

    return HeartRateRecord(
        startTime = startTime,
        startZoneOffset = startOffset,
        endTime = endTime,
        endZoneOffset = endOffset,
        samples = samples,
        metadata = metadata
    )
}
