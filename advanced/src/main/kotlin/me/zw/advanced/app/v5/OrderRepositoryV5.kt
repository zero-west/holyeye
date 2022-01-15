package me.zw.advanced.app.v5

import me.zw.advanced.trace.callback.TraceTemplate
import org.springframework.stereotype.Repository

@Repository
class OrderRepositoryV5(
    private val traceTemplate: TraceTemplate,
) {

    @Throws(Exception::class)
    fun save(itemId: String) {
        traceTemplate.execute("OrderRepository.save()"){
            if (itemId == "ex") {
                throw IllegalArgumentException("예외 발생!!")
            }
            Thread.sleep(1000)
        }
    }
}