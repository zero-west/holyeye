package me.zw.advanced.threadlocal

import me.zw.advanced.threadlocal.code.ThreadLocalService
import mu.KotlinLogging
import org.junit.jupiter.api.Test

class ThreadLocalServiceTest {

    private val logger = KotlinLogging.logger { }
    private val fieldService = ThreadLocalService()

    @Test
    fun field() {
        logger.info { "main start" }

        val threadA = Thread { fieldService.logic("userA") }
        threadA.name = "thread-A"

        val threadB = Thread { fieldService.logic("userB") }
        threadB.name = "thread-B"

        threadA.start()
//        Thread.sleep(2000)
        Thread.sleep(100)
        threadB.start()

        Thread.sleep(2000)
        logger.info { "main exit" }
    }
}