package me.zw.advanced.app.v3

import me.zw.advanced.trace.logtrace.LogTrace
import org.springframework.stereotype.Service

@Service
class OrderServiceV3(
    private val orderRepositoryV3: OrderRepositoryV3,
    private val logTrace: LogTrace,
) {

    @Throws(Exception::class)
    fun orderItem(itemId: String) {
        val status = logTrace.begin("OrderService.orderItem()")
        try {
            orderRepositoryV3.save(itemId)
            logTrace.end(status)
        } catch (e: Exception) {
            logTrace.exception(status, e)
            throw e
        }
    }

}