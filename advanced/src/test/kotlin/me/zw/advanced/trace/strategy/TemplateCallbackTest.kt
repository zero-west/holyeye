package me.zw.advanced.trace.strategy

import me.zw.advanced.trace.strategy.code.template.TimeLogTemplate
import mu.KotlinLogging
import org.junit.jupiter.api.Test

class TemplateCallbackTest {

    private val logger = KotlinLogging.logger { }

    @Test
    fun callbackV1() {
        val template = TimeLogTemplate()
        template.execute { logger.info { "비즈니스 로직1 실행" } }

        template.execute { logger.info { "비즈니스 로직2 실행" } }
    }
}