package me.zw.advanced.trace.strategy.code

import mu.KotlinLogging

class ContextV2 {

    private val logger = KotlinLogging.logger { }

    fun execute(strategy: Strategy) {
        val startTime = System.currentTimeMillis()

        // 비즈니스 로직 실행
        strategy.call()

        val endTime = System.currentTimeMillis()
        val resultTime = endTime - startTime

        logger.info { "resultTime=$resultTime" }
    }
}