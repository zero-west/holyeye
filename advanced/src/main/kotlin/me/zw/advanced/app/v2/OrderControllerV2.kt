package me.zw.advanced.app.v2

import me.zw.advanced.trace.hellotrace.HelloTraceV2
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderControllerV2(
    private val orderServiceV2: OrderServiceV2,
    private val helloTraceV2: HelloTraceV2,
) {

    @GetMapping("/v2/request")
    fun request(itemId: String): String {
        val status = helloTraceV2.begin("OrderController.request()")
        try {
            orderServiceV2.orderItem(status.traceId, itemId)
            helloTraceV2.end(status)
            return "OK"
        } catch (e: Exception) {
            helloTraceV2.exception(status, e)
            throw e
        }
    }

}