package me.zw.advanced.app.v5

import me.zw.advanced.trace.callback.TraceTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderControllerV5(
    private val orderServiceV5: OrderServiceV5,
    private val traceTemplate: TraceTemplate,
) {

    @GetMapping("/v5/request")
    fun request(itemId: String): String {
        return traceTemplate.execute("OrderController.request()") {
            orderServiceV5.orderItem(itemId)
            "OK"
        }
    }

}