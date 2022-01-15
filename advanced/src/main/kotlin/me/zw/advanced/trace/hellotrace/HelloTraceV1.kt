package me.zw.advanced.trace.hellotrace

import me.zw.advanced.trace.TraceId
import me.zw.advanced.trace.TraceStatus
import mu.KotlinLogging
import org.springframework.stereotype.Component

@Component
class HelloTraceV1 {

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

    fun begin(message: String): TraceStatus {
        val traceId = TraceId()
        val startTimeMs = System.currentTimeMillis()
        logger.info { "[${traceId.id}] ${addSpace(START_PREFIX, traceId.level)}${message}" }

        return TraceStatus(traceId, startTimeMs, message)
    }

    fun end(status: TraceStatus) {
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
    }

    fun exception(status: TraceStatus, e: Exception) {
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

    }

}