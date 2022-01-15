package me.zw.advanced.trace.strategy

import me.zw.advanced.trace.strategy.code.ContextV1
import me.zw.advanced.trace.strategy.code.Strategy
import me.zw.advanced.trace.strategy.code.StrategyLogic1
import me.zw.advanced.trace.strategy.code.StrategyLogic2
import mu.KotlinLogging
import org.junit.jupiter.api.Test

class ContextV1Test {

    private val logger = KotlinLogging.logger { }

    @Test
    fun strategyV0() {
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
     * 전략 패턴 사용
     */
    @Test
    fun strategyV1() {
        val strategyLogic1 = StrategyLogic1()
        val context1 = ContextV1(strategyLogic1)
        context1.execute()

        val strategyLogic2 = StrategyLogic2()
        val context2 = ContextV1(strategyLogic2)
        context2.execute()
    }

    @Test
    fun strategyV2() {
        val strategyLogic1 = object : Strategy {
            override fun call() {
                logger.info { "비즈니스 로직1 실행" }
            }
        }
        val context1 = ContextV1(strategyLogic1)
        context1.execute()

        val strategyLogic2 = object : Strategy {
            override fun call() {
                logger.info { "비즈니스 로직2 실행" }
            }
        }
        val context2 = ContextV1(strategyLogic2)
        context2.execute()
    }

    @Test
    fun strategyV3() {
        val context1 = ContextV1(object : Strategy {
            override fun call() {
                logger.info { "비즈니스 로직1 실행" }
            }
        })
        context1.execute()

        val context2 = ContextV1(object : Strategy {
            override fun call() {
                logger.info { "비즈니스 로직2 실행" }
            }
        })
        context2.execute()
    }



}