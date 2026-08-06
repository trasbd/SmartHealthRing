@file:Suppress("unused")

package com.trasbd.ringbridge.protocol

import android.content.Context
import com.trasbd.lib.ILogger
import com.trasbd.lib.utilities.getLifecycleScope
import com.trasbd.ringbridge.ble.RingClient
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.TimeZone

class PowerStats(context: Context, private val logger: ILogger) {

    companion object {
        const val POWER_GROUP = 2
        const val POWER_TYPE = 37
        const val OFFSET_2000 = RingClient.OFFSET_2000 // seconds
    }

    // 1. Create a scope that tied to the Context's lifecycle
    // If context isn't a lifecycle owner, we fall back to a default MainScope
    private val internalScope = context.getLifecycleScope() ?: MainScope()

    /* -----------------------------
     * Public read-only state
     * ----------------------------- */

    val lastChargingTimeMs get() = _lastChargingTimeMs.asStateFlow()


    val usageTime get() = _usageTime.asStateFlow()
    val screenDuration get() = _screenDuration.asStateFlow()
    val callDuration get() = _callDuration.asStateFlow()
    val musicDuration get() = _musicDuration.asStateFlow()
    val healthMeasurementDuration get() = _healthMeasurementDuration.asStateFlow()
    val messagesNumber get() = _messagesNumber.asStateFlow()
    val lastChargingEndBattery get() = _lastChargingEndBattery.asStateFlow()
    val batteryLevel get() = _batteryLevel.asStateFlow()
    val aeratedBloodPressure get() = _aeratedBloodPressure.asStateFlow()

    /* -----------------------------
     * Internal mutable state
     * ----------------------------- */

    private val _lastChargingTimeMs = MutableStateFlow<Long?>(null)

    private val _usageTime = MutableStateFlow<Int?>(null)
    private val _screenDuration = MutableStateFlow<Int?>(null)
    private val _callDuration = MutableStateFlow<Int?>(null)
    private val _musicDuration = MutableStateFlow<Int?>(null)
    private val _healthMeasurementDuration = MutableStateFlow<Int?>(null)
    private val _messagesNumber = MutableStateFlow<Int?>(null)

    private val _lastChargingEndBattery = MutableStateFlow<Int?>(null)
    private val _batteryLevel = MutableStateFlow<Int?>(null)

    private val _aeratedBloodPressure = MutableStateFlow<Int?>(null)

    val chargeDateTime: StateFlow<LocalDateTime?> = _lastChargingTimeMs
        .map { ms ->
            ms?.let {
                LocalDateTime.ofInstant(Instant.ofEpochMilli(it), ZoneId.systemDefault())
            }
        }
        .stateIn(
            scope = internalScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )


    /* -----------------------------
     * Ingest BLE payload
     * ----------------------------- */

    fun ingest(payload: ByteArray) {
        if (payload.size < 34) {
            throw IllegalArgumentException("Power statistics payload too short")
        }

        val tzOffsetMs = TimeZone.getDefault().getOffset(System.currentTimeMillis())

        /* ---- last charging time ---- */
        val lastChargeSec =
            (payload[0].toInt() and 0xFF) or
                    ((payload[1].toInt() and 0xFF) shl 8) or
                    ((payload[2].toInt() and 0xFF) shl 16) or
                    ((payload[3].toInt() and 0xFF) shl 24)

        val lastChargingTimeMs =
            (lastChargeSec + OFFSET_2000) * 1000L - tzOffsetMs

        /* ---- durations / counters ---- */
        val usageTime =
            (payload[4].toInt() and 0xFF) or
                    ((payload[5].toInt() and 0xFF) shl 8) or
                    ((payload[6].toInt() and 0xFF) shl 16) or
                    ((payload[7].toInt() and 0xFF) shl 24)

        val screenDuration =
            (payload[8].toInt() and 0xFF) or
                    ((payload[9].toInt() and 0xFF) shl 8) or
                    ((payload[10].toInt() and 0xFF) shl 16) or
                    ((payload[11].toInt() and 0xFF) shl 24)

        val callDuration =
            (payload[12].toInt() and 0xFF) or
                    ((payload[13].toInt() and 0xFF) shl 8) or
                    ((payload[14].toInt() and 0xFF) shl 16) or
                    ((payload[15].toInt() and 0xFF) shl 24)

        val musicDuration =
            (payload[16].toInt() and 0xFF) or
                    ((payload[17].toInt() and 0xFF) shl 8) or
                    ((payload[18].toInt() and 0xFF) shl 16) or
                    ((payload[19].toInt() and 0xFF) shl 24)

        val healthMeasurementDuration =
            (payload[20].toInt() and 0xFF) or
                    ((payload[21].toInt() and 0xFF) shl 8) or
                    ((payload[22].toInt() and 0xFF) shl 16) or
                    ((payload[23].toInt() and 0xFF) shl 24)

        val messagesNumber =
            (payload[24].toInt() and 0xFF) or
                    ((payload[25].toInt() and 0xFF) shl 8) or
                    ((payload[26].toInt() and 0xFF) shl 16) or
                    ((payload[27].toInt() and 0xFF) shl 24)

        /* ---- battery ---- */
        val lastChargingEndBattery = payload[28].toInt() and 0xFF
        val batteryLevel = payload[29].toInt() and 0xFF

        /* ---- aerated blood pressure ---- */
        val aeratedBloodPressure =
            (payload[30].toInt() and 0xFF) or
                    ((payload[31].toInt() and 0xFF) shl 8) or
                    ((payload[32].toInt() and 0xFF) shl 16) or
                    ((payload[33].toInt() and 0xFF) shl 24)

        /* ---- publish ---- */
        _lastChargingTimeMs.value = lastChargingTimeMs

        _usageTime.value = usageTime
        _screenDuration.value = screenDuration
        _callDuration.value = callDuration
        _musicDuration.value = musicDuration
        _healthMeasurementDuration.value = healthMeasurementDuration
        _messagesNumber.value = messagesNumber

        _lastChargingEndBattery.value = lastChargingEndBattery
        _batteryLevel.value = batteryLevel

        _aeratedBloodPressure.value = aeratedBloodPressure
    }
}
