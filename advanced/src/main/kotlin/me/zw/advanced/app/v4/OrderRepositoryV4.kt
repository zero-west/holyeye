package me.zw.advanced.app.v4

import me.zw.advanced.trace.logtrace.LogTrace
import me.zw.advanced.trace.template.AbstractTemplate
import org.springframework.stereotype.Repository

@Repository
class OrderRepositoryV4(
    private val logTrace: LogTrace,
) {

    @Throws(Exception::class)
    fun save(itemId: String) {

        val template = object : AbstractTemplate<Unit>(logTrace) {
            override fun call() {
                if (itemId == "ex") {
                    throw IllegalArgumentException("예외 발생!!")
                }
                Thread.sleep(1000)
            }
        }

        template.execute("OrderRepository.save()")
    }
}