package me.zw.advanced.trace.strategy.code

import mu.KotlinLogging

/**
 * 필드에 전략을 보관하는 방식
 * 변하지 않는 로직을 가지고 있는 템플릿 역할을 하는 코드. 전략패턴에서는 이를 컨텍스트(문맥)이라 한다.
 */
class ContextV1(
    private val strategy: Strategy,
) {

    private val logger = KotlinLogging.logger { }

    fun execute() {
        val startTime = System.currentTimeMillis()

        // 비즈니스 로직 실행
        strategy.call()

        val endTime = System.currentTimeMillis()
        val resultTime = endTime - startTime

        logger.info { "resultTime=$resultTime" }
    }
}