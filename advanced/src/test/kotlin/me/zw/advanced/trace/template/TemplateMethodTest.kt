package me.zw.advanced.trace.template

import me.zw.advanced.trace.template.code.AbstractTemplate
import me.zw.advanced.trace.template.code.SubClassLogic1
import me.zw.advanced.trace.template.code.SubClassLogic2
import mu.KotlinLogging
import org.junit.jupiter.api.Test

class TemplateMethodTest {

    private val logger = KotlinLogging.logger { }

    @Test
    fun templateMethodV0() {
        logic1()
        logic2()
    }


    private fun logic1() {
        val startTime = System.currentTimeMillis()

        // 비즈니스 로직 실행
        logger.info { "비즈니스 로직1 실행" }

        // 비즈니스 로직 종료
        val endTime = System.currentTimeMillis()
        val resultTime = endTime - startTime

        logger.info { "resultTime=$resultTime" }
    }

    private fun logic2() {
        val startTime = System.currentTimeMillis()

        // 비즈니스 로직 실행
        logger.info { "비즈니스 로직2 실행" }

        // 비즈니스 로직 종료
        val endTime = System.currentTimeMillis()
        val resultTime = endTime - startTime

        logger.info { "resultTime=$resultTime" }
    }

    /**
     * 템플릿 메서드 패턴 적용
     */
    @Test
    fun templateMethodV1() {
        val template1 = SubClassLogic1()
        template1.execute()

        val template2 = SubClassLogic2()
        template2.execute()
    }

    /**
     * Template method 패턴 적용, 익명 클래스 사용
     *
     */
    @Test
    fun templateMethodV2() {
        val template1 = object : AbstractTemplate() {
            override fun call() {
                logger.info { "비즈니스 로직1 실행" }
            }
        }
        template1.execute()

        val template2 = object : AbstractTemplate() {
            override fun call() {
                logger.info { "비즈니스 로직2 실행" }
            }
        }
        template2.execute()
    }



}