package me.zw.advanced.trace.logtrace

import me.zw.advanced.trace.TraceId
import me.zw.advanced.trace.TraceStatus
import mu.KotlinLogging

class FieldLogTrace : LogTrace {
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

    private lateinit var traceIdHolder: TraceId

    override fun begin(message: String): TraceStatus {
        syncTraceId()
        val traceId = traceIdHolder

        val startTimeMs = System.currentTimeMillis()
        logger.info { "[${traceId.id}] ${addSpace(START_PREFIX, traceId.level)}${message}" }

        return TraceStatus(traceId, startTimeMs, message)
    }

    private fun syncTraceId() {
        if (!::traceIdHolder.isInitialized) {
            traceIdHolder = TraceId()
        } else {
            traceIdHolder = traceIdHolder.createNextId()
        }
    }


    override fun end(status: TraceStatus) {
        val stopTimeMs = System.currentTimeMillis()
        val resultTimeMs: Long = stopTimeMs - status.startTimeMs
        val traceId: TraceId = status.traceId

        logger.info {
            "[${traceId.id}] ${
                addSpace(
                    COMPLETE_PREFIX,
                    traceId.level
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
                    EX_PREFIX,
                    traceId.level
                )
            }${status.message} time=${resultTimeMs}ms ex=${e}"
        }

        releasesTraceId()
    }

    private fun releasesTraceId() {
        traceIdHolder = traceIdHolder.createPreviousId()
    }

}