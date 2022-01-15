package me.zw.advanced.trace.strategy.code.template

import mu.KotlinLogging

class TimeLogTemplate {

    private val logger = KotlinLogging.logger { }

//    fun execute(callback: Callback){
    fun execute(callback: () -> Unit) {
        val startTime = System.currentTimeMillis()

        // 비즈니스 로직 실행
        callback.invoke()

        val endTime = System.currentTimeMillis()
        val resultTime = endTime - startTime

        logger.info { "resultTime=$resultTime" }
    }
}