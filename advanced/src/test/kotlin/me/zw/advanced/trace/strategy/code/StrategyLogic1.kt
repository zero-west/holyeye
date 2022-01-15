package me.zw.advanced.trace.strategy.code

import mu.KotlinLogging

class StrategyLogic1 : Strategy {

    private val logger = KotlinLogging.logger { }

    override fun call() {
        logger.info { "비즈니스 로직1 실행" }
    }
}