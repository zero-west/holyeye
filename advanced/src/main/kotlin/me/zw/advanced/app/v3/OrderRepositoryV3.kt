package me.zw.advanced.app.v3

import me.zw.advanced.trace.logtrace.LogTrace
import org.springframework.stereotype.Repository

@Repository
class OrderRepositoryV3(
    private val logTrace: LogTrace,
) {

    @Throws(Exception::class)
    fun save(itemId: String) {
        val status = logTrace.begin("OrderRepository.save()")
        try {
            if (itemId == "ex") {
                throw IllegalArgumentException("예외 발생!!")
            }
            Thread.sleep(1000)
            logTrace.end(status)
        } catch (e: Exception) {
            logTrace.exception(status, e)
            throw e
        }
    }
}