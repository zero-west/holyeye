package me.zw.advanced.app.v2

import me.zw.advanced.trace.TraceId
import me.zw.advanced.trace.hellotrace.HelloTraceV2
import org.springframework.stereotype.Repository

@Repository
class OrderRepositoryV2(
    private val helloTraceV2: HelloTraceV2,
) {

    @Throws(Exception::class)
    fun save(traceId: TraceId, itemId: String) {
        val status = helloTraceV2.beginSync(traceId, "OrderRepository.save()")
        try {
            if (itemId == "ex") {
                throw IllegalArgumentException("예외 발생!!")
            }
            Thread.sleep(1000)
            helloTraceV2.end(status)
        } catch (e: Exception) {
            helloTraceV2.exception(status, e)
            throw e
        }
    }
}