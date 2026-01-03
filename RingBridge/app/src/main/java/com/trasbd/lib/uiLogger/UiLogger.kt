@file:Suppress("unused")

package com.trasbd.lib.uiLogger

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import com.trasbd.lib.ILogger
import java.time.LocalTime


class UiLogger : ILogger {

    private val _lines = mutableStateListOf<LogLine>()
    val lines: List<LogLine> = _lines

    private fun addLine(level: String, tag: String, msg: String) {
        val ts = LocalTime.now().toString()
        _lines.add(LogLine(ts, level, "[$tag] $msg"))
    }

    override fun d(tag: String, msg: String) {
        Log.d(tag, msg)
        addLine("D", tag, msg)
    }

    override fun i(tag: String, msg: String) {
        Log.i(tag, msg)
        addLine("I", tag, msg)
    }

    override fun w(tag: String, msg: String, t: Throwable?) {
        Log.w(tag, msg, t)
        addLine("W", tag, msg)
    }

    override fun e(tag: String, msg: String, t: Throwable?) {
        Log.e(tag, msg, t)
        addLine("E", tag, msg)
    }

    fun log(tag:String, msg: String, level: Int = Log.DEBUG)
    {
        when (level){
            Log.DEBUG -> this.d(tag, msg)
        }
    }

    fun clear() = _lines.clear()
}
