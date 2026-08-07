@file:Suppress("unused", "unused")

package com.trasbd.ringbridge.protocol

import com.trasbd.ringbridge.ble.RingClient
import java.time.Instant
import java.util.Calendar

@Suppress("unused")
object BleTime {

    /** Command ID used to set device time */
    const val SET_TIME_CMD: Int = 256

    val SET_TIME_GROUP = RingClient.decodeCmd(SET_TIME_CMD).first
    val SET_TIME_TYPE = RingClient.decodeCmd(SET_TIME_CMD).second


    fun encode(instant: Instant = Instant.now()): ByteArray {
        val calendar = Calendar.getInstance().apply {
            timeInMillis = instant.toEpochMilli()
        }

        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        val second = calendar.get(Calendar.SECOND)
        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)

        val bleDayOfWeek =
            if (dayOfWeek == Calendar.SUNDAY) 6 else dayOfWeek - 2

        return byteArrayOf(
            (year and 0xFF).toByte(),
            ((year shr 8) and 0xFF).toByte(),
            month.toByte(),
            day.toByte(),
            hour.toByte(),
            minute.toByte(),
            second.toByte(),
            bleDayOfWeek.toByte()
        )
    }
}