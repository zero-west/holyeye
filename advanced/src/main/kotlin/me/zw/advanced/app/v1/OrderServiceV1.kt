package me.zw.advanced.app.v1

import me.zw.advanced.trace.hellotrace.HelloTraceV1
import org.springframework.stereotype.Service

@Service
class OrderServiceV1(
    private val orderRepositoryV1: OrderRepositoryV1,
    private val helloTraceV1: HelloTraceV1,
) {

    @Throws(Exception::class)
    fun orderItem(itemId: String) {
        val status = helloTraceV1.begin("OrderService.orderItem()")

        try {
            orderRepositoryV1.save(itemId)
            helloTraceV1.end(status)
        } catch (e: Exception) {
            helloTraceV1.exception(status, e)
            throw e
        }
    }

}