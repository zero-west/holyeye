package me.zw.advanced.app.v4

import me.zw.advanced.trace.logtrace.LogTrace
import me.zw.advanced.trace.template.AbstractTemplate
import org.springframework.stereotype.Service

@Service
class OrderServiceV4(
    private val orderRepositoryV4: OrderRepositoryV4,
    private val logTrace: LogTrace,
) {

    @Throws(Exception::class)
    fun orderItem(itemId: String) {

        val template = object : AbstractTemplate<Unit>(logTrace) {
            override fun call() {
                orderRepositoryV4.save(itemId)
            }
        }

        template.execute("OrderService.orderItem()")
    }

}