package me.zw.advanced.trace.logtrace

import me.zw.advanced.trace.TraceId
import me.zw.advanced.trace.TraceStatus
import mu.KotlinLogging

class ThreadLocalLogTrace : LogTrace {
    private val logger = KotlinLogging.logger { }

    companion object {
        private const val START_PREFIX = "-->"
        private const val COMPLETE_PREFIX = "<--"
        private const val EX_PREFIX = "<X-"

        fun addSpace(prefix: String, level: Int): String {
            val sb = StringBuilder()
            for (i in 0 until level) {
                sb.append(if (i == level - 1) "|$prefix" else "|   ")
            }
            return sb.toString()
        }
    }

    private val traceIdHolder = ThreadLocal<TraceId?>()

    override fun begin(message: String): TraceStatus {
        syncTraceId()
        val traceId = traceIdHolder.get()!!

        val startTimeMs = System.currentTimeMillis()
        logger.info { "[${traceId.id}] ${addSpace(START_PREFIX, traceId.level)}${message}" }

        return TraceStatus(traceId, startTimeMs, message)
    }

    private fun syncTraceId() {
        val traceId = traceIdHolder.get()

        if (traceId == null) {
            traceIdHolder.set(TraceId())
        } else {
            traceIdHolder.set(traceId.createNextId())
        }
    }


    override fun end(status: TraceStatus) {
        val stopTimeMs = System.currentTimeMillis()
        val resultTimeMs: Long = stopTimeMs - status.startTimeMs
        val traceId: TraceId = status.traceId

        logger.info {
            "[${traceId.id}] ${
                addSpace(
                    COMPLETE_PREFIX, traceId.level
                )
            }${status.message} time=${resultTimeMs}ms"
        }

        releasesTraceId()
    }

    override fun exception(status: TraceStatus, e: Exception) {
        val stopTimeMs = System.currentTimeMillis()
        val resultTimeMs: Long = stopTimeMs - status.startTimeMs
        val traceId: TraceId = status.traceId

        logger.info {
            "[${traceId.id}] ${
                addSpace(
                    EX_PREFIX, traceId.level
                )
            }${status.message} time=${resultTimeMs}ms ex=${e}"
        }

        releasesTraceId()
    }

    private fun releasesTraceId() {
        val traceId = traceIdHolder.get()!!
        if (traceId.isFirstLevel()) {
            traceIdHolder.remove()
        } else {
            traceIdHolder.set(traceId.createPreviousId())
        }
    }

}