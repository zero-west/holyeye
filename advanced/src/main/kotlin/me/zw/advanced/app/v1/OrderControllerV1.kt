package me.zw.advanced.app.v1

import me.zw.advanced.trace.hellotrace.HelloTraceV1
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderControllerV1(
    private val orderServiceV1: OrderServiceV1,
    private val helloTraceV1: HelloTraceV1,
) {

    @GetMapping("/v1/request")
    fun request(itemId: String): String {
        val status = helloTraceV1.begin("OrderController.request()")
        try {
            orderServiceV1.orderItem(itemId)
            helloTraceV1.end(status)
            return "OK"
        } catch (e: Exception) {
            helloTraceV1.exception(status, e)
            throw e
        }
    }

}