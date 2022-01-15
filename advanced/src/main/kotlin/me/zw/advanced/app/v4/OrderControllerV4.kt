package me.zw.advanced.app.v4

import me.zw.advanced.trace.logtrace.LogTrace
import me.zw.advanced.trace.template.AbstractTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderControllerV4(
    private val orderServiceV4: OrderServiceV4,
    private val logTrace: LogTrace,
) {

    @GetMapping("/v4/request")
    fun request(itemId: String): String {

        val template = object : AbstractTemplate<String>(logTrace) {
            override fun call(): String {
                orderServiceV4.orderItem(itemId)
                return "OK"
            }
        }

        return template.execute("OrderController.request()")
    }

}