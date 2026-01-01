package com.trasbd.ringbridge.ui.uiLogger

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import java.time.LocalTime

class UiLogger {
    private val _lines = mutableStateListOf<LogLine>()
    val lines: List<LogLine> = _lines

    fun log(level: String, msg: String) {
        Log.d("UiLogger", "$level: $msg")
        val ts = LocalTime.now().toString()
        _lines.add(LogLine(ts, level, msg))
    }

    fun clear() = _lines.clear()
}