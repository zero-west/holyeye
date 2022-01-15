package me.zw.advanced.trace.template.code

import mu.KotlinLogging

abstract class AbstractTemplate {

    protected val logger = KotlinLogging.logger { }

    fun execute() {
        val startTime = System.currentTimeMillis()

//        // 비즈니스 로직 실행
//        logger.info { "비즈니스 로직1 실행" }

        call()
//        // 비즈니스 로직 종료
        val endTime = System.currentTimeMillis()
        val resultTime = endTime - startTime

        logger.info { "resultTime=$resultTime" }
    }


    protected abstract fun call()


}