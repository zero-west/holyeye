package me.zw.advanced.app.v3

import me.zw.advanced.trace.logtrace.LogTrace
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderControllerV3(
    private val orderServiceV3: OrderServiceV3,
    private val logTrace: LogTrace,
) {

    @GetMapping("/v3/request")
    fun request(itemId: String): String {
        val status = logTrace.begin("OrderController.request()")
        try {
            orderServiceV3.orderItem(itemId)
            logTrace.end(status)
            return "OK"
        } catch (e: Exception) {
            logTrace.exception(status, e)
            throw e
        }
    }

}