package me.zw.advanced.trace.callback

import me.zw.advanced.trace.logtrace.LogTrace

class TraceTemplate(
    private val logTrace: LogTrace,
) {

    fun <T> execute(
        message: String,
        callback: () -> T
    ): T {
        val status = logTrace.begin(message)
        try {
            val result: T = callback.invoke()
            logTrace.end(status)
            return result
        } catch (e: Exception) {
            logTrace.exception(status, e)
            throw e
        }
    }
}