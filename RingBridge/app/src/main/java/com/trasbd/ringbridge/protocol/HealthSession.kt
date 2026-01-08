@file:Suppress("SpellCheckingInspection")

package com.trasbd.ringbridge.protocol

import androidx.health.connect.client.records.SleepSessionRecord
import com.trasbd.lib.ILogger
import com.trasbd.ringbridge.ble.RingClient
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class HealthSession(logger: ILogger) {
    companion object {

        const val HEALTH_GROUP = 5
        const val SLEEP_HEALTH_TYPE = 4
        const val ALL_HEALTH_TYPE = 9
        val HEALTH_TYPES = intArrayOf(SLEEP_HEALTH_TYPE, 8, ALL_HEALTH_TYPE)


        const val HR_LIVE_CMD_GROUP = 3
        const val HR_LIVE_CMD_TYPE = 47
        val HR_LIVE_START_PAYLOAD = byteArrayOf(0x01, 0x00)
        val HR_LIVE_STOP_PAYLOAD = byteArrayOf(0x00, 0x00)
        const val HR_LIVE_GROUP = 6
        const val HR_LIVE_TYPE = 1

        const val END_SUBTYPE = 128
        const val END_COMMAND = 1408
        val END_PAYLOAD = byteArrayOf()

        val DELETE_PAYLOAD = byteArrayOf(0x02)
        val DELETE_HEALTH_CMD = listOf(1344, 1346, 1347, 1348)
        const val DELETE_SLEEP_CMD = 1345

        val SLEEP_TYPES = mapOf<Int, Int>(
            241 to SleepSessionRecord.STAGE_TYPE_DEEP,
            242 to SleepSessionRecord.STAGE_TYPE_LIGHT,
            243 to SleepSessionRecord.STAGE_TYPE_REM,
            244 to SleepSessionRecord.STAGE_TYPE_AWAKE
        )

        const val OFFSET_2000 = RingClient.OFFSET_2000 // seconds
    }

    var healthType: Int? = null
    var blocks = mutableListOf<ByteArray>()
    var complete: Boolean = false

    var start: Instant? = null
    var end: Instant? = null

    fun ingest(group: Int, subtype: Int, payload: ByteArray) {
        if ((HEALTH_TYPES.contains(subtype) && group == HEALTH_GROUP) || (group == HR_LIVE_GROUP && subtype == HR_LIVE_CMD_TYPE && payload.contentEquals(
                HR_LIVE_START_PAYLOAD
            ))
        ) {
            start = Instant.now()
            healthType = RingClient.cmd(group, subtype)
            blocks = mutableListOf()
            complete = false
            return
        }

        if (healthType == null) {
            return
        }

        if ((group == HR_LIVE_GROUP && subtype == HR_LIVE_CMD_TYPE && payload.contentEquals(
                HR_LIVE_STOP_PAYLOAD
            ))
        ) {
            end = Instant.now()
            complete = true
            return
        }

        blocks.add(payload)

        if ((group == HEALTH_GROUP && subtype == END_SUBTYPE)) {
            end = Instant.now()
            complete = true
        }
    }

    fun parse(): Any {
        val type = healthType ?: throw IllegalStateException("No health session active")

        // Concatenate payload blocks
        val totalLen = blocks.sumOf { it.size }
        val raw = ByteArray(totalLen)

        var offset = 0
        for (b in blocks) {
            System.arraycopy(b, 0, raw, offset, b.size)
            offset += b.size
        }

        return unpackHealthData(raw, type, start, end)
    }

    fun unpackHealthData(raw: ByteArray, healthType: Int, start: Instant?, end: Instant?): Any {
        return when (healthType) {
            RingClient.cmd(HEALTH_GROUP, SLEEP_HEALTH_TYPE) -> unpackSleepData(raw, healthType)
            RingClient.cmd(HEALTH_GROUP, ALL_HEALTH_TYPE) -> unpackHealthHistoryAll(raw, healthType)
            RingClient.cmd(HR_LIVE_CMD_GROUP, HR_LIVE_CMD_TYPE) -> unpackLiveHRData(
                raw,
                healthType,
                start,
                end
            )

            else -> throw NotImplementedError(
                "Health type $healthType not implemented yet"
            )
        }
    }

    @Suppress("unused")
    private fun unpackLiveHRData(
        raw: ByteArray,
        healthType: Int,
        start: Instant?,
        end: Instant? = Instant.now()
    ): LiveHRSession {

        requireNotNull(start)
        requireNotNull(end)
        require(end.isAfter(start)) {
            "Live HR end must be after start (start=$start, end=$end)"
        }

        return LiveHRSession(start, end, raw.map { it.toInt() and 0xFF })
    }


    @Suppress("unused", "UnusedVariable")
    fun unpackHealthHistoryAll(
        raw: ByteArray, healthType: Int
    ): HealthHistoryResult {


        // Match Python: time.localtime().tm_gmtoff
        val tzOffsetMs = TimeZone.getDefault().rawOffset.toLong()

        val records = mutableListOf<HealthHistoryRecord>()

        val recordLength = 20
        var i = 0

        val dateFormat = SimpleDateFormat("yyyyMMdd HHmmss", Locale.US)

        while (i + recordLength <= raw.size) {

            // ---- timestamp ----
            val tsSec =
                (raw[i].toInt() and 0xFF) or ((raw[i + 1].toInt() and 0xFF) shl 8) or ((raw[i + 2].toInt() and 0xFF) shl 16) or ((raw[i + 3].toInt() and 0xFF) shl 24)

            val startTime = ((tsSec.toLong() + OFFSET_2000) * 1000L) - tzOffsetMs

            val startDateTime = dateFormat.format(Date(startTime))

            // ---- fields (EXACT mapping) ----
            val stepValue = (raw[i + 4].toInt() and 0xFF) or ((raw[i + 5].toInt() and 0xFF) shl 8)

            val heartValue = raw[i + 6].toInt() and 0xFF
            val sbpValue = raw[i + 7].toInt() and 0xFF   // unused
            val dbpValue = raw[i + 8].toInt() and 0xFF   // unused
            val ooValue = raw[i + 9].toInt() and 0xFF
            val respiratoryRate = raw[i + 10].toInt() and 0xFF
            val hrvValue = raw[i + 11].toInt() and 0xFF
            val cvrrValue = raw[i + 12].toInt() and 0xFF
            val tempInt = raw[i + 13].toInt() and 0xFF
            val tempFloat = raw[i + 14].toInt() and 0xFF
            val bodyFatInt = raw[i + 15].toInt() and 0xFF // unused
            val bodyFatFloat = raw[i + 16].toInt() and 0xFF // unused
            val bloodSugar = raw[i + 17].toInt() and 0xFF // unused

            records.add(
                HealthHistoryRecord(
                    startTime = startTime,
                    startDateTime = startDateTime,
                    stepValue = stepValue,
                    heartValue = heartValue,
                    ooValue = ooValue,
                    respiratoryRateValue = respiratoryRate,
                    hrvValue = hrvValue,
                    cvrrValue = cvrrValue,
                    tempIntValue = tempInt,
                    tempFloatValue = tempFloat,
                )
            )

            i += recordLength
        }

        return HealthHistoryResult(
            dataType = healthType, data = records
        )
    }

    fun unpackSleepData(raw: ByteArray, healthType: Int): SleepResult {

        val tzOffsetMs = TimeZone.getDefault().rawOffset.toLong()

        val sessions = mutableListOf<SleepSession>()

        val dateFormat = SimpleDateFormat("yyyyMMdd HHmmss", Locale.US)

        var i = 0
        val length = raw.size

        while (i + 20 <= length) {

            val sessionStart = i

            // ---- session header ----
            val sessionLen = (raw[i + 2].toInt() and 0xFF) or ((raw[i + 3].toInt() and 0xFF) shl 8)

            val startSec =
                (raw[i + 4].toInt() and 0xFF) or ((raw[i + 5].toInt() and 0xFF) shl 8) or ((raw[i + 6].toInt() and 0xFF) shl 16) or ((raw[i + 7].toInt() and 0xFF) shl 24)

            val endSec =
                (raw[i + 8].toInt() and 0xFF) or ((raw[i + 9].toInt() and 0xFF) shl 8) or ((raw[i + 10].toInt() and 0xFF) shl 16) or ((raw[i + 11].toInt() and 0xFF) shl 24)

            val startTime = ((startSec.toLong() + OFFSET_2000) * 1000L) - tzOffsetMs

            val endTime = ((endSec.toLong() + OFFSET_2000) * 1000L) - tzOffsetMs

            val startDateTime = dateFormat.format(Date(startTime))
            val endDateTime = dateFormat.format(Date(endTime))

            val deepSleepCount =
                (raw[i + 12].toInt() and 0xFF) or ((raw[i + 13].toInt() and 0xFF) shl 8)

            // ---- dual interpretation block (EXACT Java behavior) ----
            val remTotal: Int
            val deepTotal: Int
            val lightTotal: Int
            val lightCount: Int

            if (deepSleepCount == 0xFFFF) {
                remTotal = (raw[i + 14].toInt() and 0xFF) or ((raw[i + 15].toInt() and 0xFF) shl 8)

                deepTotal = (raw[i + 16].toInt() and 0xFF) or ((raw[i + 17].toInt() and 0xFF) shl 8)

                lightTotal =
                    (raw[i + 18].toInt() and 0xFF) or ((raw[i + 19].toInt() and 0xFF) shl 8)

                lightCount = 0
            } else {
                lightCount =
                    (raw[i + 14].toInt() and 0xFF) or ((raw[i + 15].toInt() and 0xFF) shl 8)

                remTotal = 0

                deepTotal =
                    ((raw[i + 16].toInt() and 0xFF) or ((raw[i + 17].toInt() and 0xFF) shl 8)) * 60

                lightTotal =
                    ((raw[i + 18].toInt() and 0xFF) or ((raw[i + 19].toInt() and 0xFF) shl 8)) * 60
            }

            // ---- parse sleep segments ----
            val sleepSegments = mutableListOf<SleepSegment>()
            val seen = HashSet<Long>()
            var wakeCount = 0
            var wakeDuration = 0

            var segPtr = sessionStart + 20
            val sessionEnd = sessionStart + sessionLen

            while (segPtr + 8 <= sessionEnd) {

                val sleepType = raw[segPtr].toInt() and 0xFF

                val segSec =
                    (raw[segPtr + 1].toInt() and 0xFF) or ((raw[segPtr + 2].toInt() and 0xFF) shl 8) or ((raw[segPtr + 3].toInt() and 0xFF) shl 16) or ((raw[segPtr + 4].toInt() and 0xFF) shl 24)

                val segTime = ((segSec.toLong() + OFFSET_2000) * 1000L) - tzOffsetMs

                val dur =
                    (raw[segPtr + 5].toInt() and 0xFF) or ((raw[segPtr + 6].toInt() and 0xFF) shl 8) or ((raw[segPtr + 7].toInt() and 0xFF) shl 16)

                if (sleepType == 244) { // wake
                    wakeCount++
                    wakeDuration += dur
                }

                if (!seen.contains(segTime)) {
                    sleepSegments.add(
                        SleepSegment(
                            sleepType = sleepType,
                            sleepStartTime = segTime,
                            sleepStartDateTime = dateFormat.format(Date(segTime)),
                            sleepLen = dur
                        )
                    )
                    seen.add(segTime)
                }

                segPtr += 8
            }

            sessions.add(
                SleepSession(
                    startTime = startTime,
                    startDateTime = startDateTime,
                    endTime = endTime,
                    endDateTime = endDateTime,
                    deepSleepCount = deepSleepCount,
                    lightSleepCount = lightCount,
                    deepSleepTotal = deepTotal,
                    lightSleepTotal = lightTotal,
                    rapidEyeMovementTotal = remTotal,
                    sleepData = sleepSegments,
                    wakeCount = wakeCount,
                    wakeDuration = wakeDuration
                )
            )

            i = segPtr // ⚠️ EXACT Java/Python behavior
        }

        return SleepResult(
            dataType = healthType, data = sessions
        )
    }

    data class LiveHRSession(
        val startTime: Instant, val endTime: Instant, val heartValues: List<Int>
    )

    data class HealthHistoryRecord(
        val startTime: Long,
        val startDateTime: String,
        val stepValue: Int,
        val heartValue: Int,
        val ooValue: Int,
        val respiratoryRateValue: Int,
        val hrvValue: Int,
        val cvrrValue: Int,
        val tempIntValue: Int,
        val tempFloatValue: Int,
    )

    data class HealthHistoryResult(
        val code: Int = 0, val dataType: Int, val data: List<HealthHistoryRecord>
    )

    data class SleepSegment(
        val sleepType: Int,
        val sleepStartTime: Long,
        val sleepStartDateTime: String,
        val sleepLen: Int
    )

    data class SleepSession(
        val startTime: Long,
        val startDateTime: String,
        val endTime: Long,
        val endDateTime: String,
        val deepSleepCount: Int,
        val lightSleepCount: Int,
        val deepSleepTotal: Int,
        val lightSleepTotal: Int,
        val rapidEyeMovementTotal: Int,
        val sleepData: List<SleepSegment>,
        val wakeCount: Int,
        val wakeDuration: Int
    )

    data class SleepResult(
        val code: Int = 0, val dataType: Int, val data: List<SleepSession>
    )


}
