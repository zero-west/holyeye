package me.zw.advanced.trace.strategy

import me.zw.advanced.trace.strategy.code.ContextV2
import me.zw.advanced.trace.strategy.code.StrategyLogic1
import me.zw.advanced.trace.strategy.code.StrategyLogic2
import org.junit.jupiter.api.Test

class ContextV2Test {
    /**
     * 전략 패턴 적용(파라미터로 넘기는 방식)
     */
    @Test
    fun strategyV1() {
        val context = ContextV2()
        context.execute(StrategyLogic1())
        context.execute(StrategyLogic2())
    }
}