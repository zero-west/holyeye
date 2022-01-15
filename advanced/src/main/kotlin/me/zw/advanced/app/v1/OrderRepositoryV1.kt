package me.zw.advanced.app.v1

import me.zw.advanced.trace.hellotrace.HelloTraceV1
import org.springframework.stereotype.Repository

@Repository
class OrderRepositoryV1(
    private val helloTraceV1: HelloTraceV1,
) {

    @Throws(Exception::class)
    fun save(itemId: String) {
        val status = helloTraceV1.begin("OrderRepository.save()")
        try {
            if (itemId == "ex") {
                throw IllegalArgumentException("예외 발생!!")
            }
            Thread.sleep(1000)
        } catch (e: Exception) {
            helloTraceV1.exception(status, e)
            throw e
        }
    }
}