package me.zw.advanced.app.v5

import me.zw.advanced.trace.callback.TraceTemplate
import org.springframework.stereotype.Service

@Service
class OrderServiceV5(
    private val orderRepositoryV5: OrderRepositoryV5,
    private val traceTemplate: TraceTemplate,
) {

    @Throws(Exception::class)
    fun orderItem(itemId: String) {
        traceTemplate.execute("OrderService.orderItem()") {
            orderRepositoryV5.save(itemId)
        }
    }

}