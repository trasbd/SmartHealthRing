package com.trasbd.lib

import android.content.Context
import android.util.Log
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class FileLogger(private val context: Context, private val fileName: String) : ILogger {

    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")

    private fun logToFile(level: String, tag: String, msg: String, t: Throwable? = null) {
        val timestamp = LocalDateTime.now().format(formatter)
        val logLine = "$timestamp $level/[$tag]: $msg${t?.let { "\n${Log.getStackTraceString(it)}" } ?: ""}\n"
        
        try {
            val file = File(context.filesDir, fileName)
            file.appendText(logLine)
        } catch (e: Exception) {
            Log.e("FileLogger", "Failed to write to log file", e)
        }
    }

    override fun d(tag: String, msg: String) {
        Log.d(tag, msg)
        logToFile("D", tag, msg)
    }

    override fun i(tag: String, msg: String) {
        Log.i(tag, msg)
        logToFile("I", tag, msg)
    }

    override fun w(tag: String, msg: String, t: Throwable?) {
        Log.w(tag, msg, t)
        logToFile("W", tag, msg, t)
    }

    override fun e(tag: String, msg: String, t: Throwable?) {
        Log.e(tag, msg, t)
        logToFile("E", tag, msg, t)
    }
}
