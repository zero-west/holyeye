package me.zw.advanced.app.v2

import me.zw.advanced.trace.TraceId
import me.zw.advanced.trace.hellotrace.HelloTraceV2
import org.springframework.stereotype.Service

@Service
class OrderServiceV2(
    private val orderRepositoryV2: OrderRepositoryV2,
    private val helloTraceV2: HelloTraceV2,
) {

    @Throws(Exception::class)
    fun orderItem(traceId: TraceId, itemId: String) {
        val status = helloTraceV2.beginSync(traceId, "OrderService.orderItem()")
        try {
            orderRepositoryV2.save(status.traceId, itemId)
            helloTraceV2.end(status)
        } catch (e: Exception) {
            helloTraceV2.exception(status, e)
            throw e
        }
    }

}