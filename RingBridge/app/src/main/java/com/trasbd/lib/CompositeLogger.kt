package com.trasbd.lib

class CompositeLogger(private val loggers: List<ILogger>) : ILogger {
    override fun d(tag: String, msg: String) {
        loggers.forEach { it.d(tag, msg) }
    }

    override fun i(tag: String, msg: String) {
        loggers.forEach { it.i(tag, msg) }
    }

    override fun w(tag: String, msg: String, t: Throwable?) {
        loggers.forEach { it.w(tag, msg, t) }
    }

    override fun e(tag: String, msg: String, t: Throwable?) {
        loggers.forEach { it.e(tag, msg, t) }
    }
}
