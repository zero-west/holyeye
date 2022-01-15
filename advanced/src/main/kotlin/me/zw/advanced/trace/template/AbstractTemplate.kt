package me.zw.advanced.trace.template

import me.zw.advanced.trace.logtrace.LogTrace

abstract class AbstractTemplate<T>(
    private val logTrace: LogTrace
) {

    protected abstract fun call(): T

    fun execute(
        message: String
    ): T {
        val status = logTrace.begin(message)
        try {
            val result: T = call()
            logTrace.end(status)
            return result
        } catch (e: Exception) {
            logTrace.exception(status, e)
            throw e
        }
    }

}