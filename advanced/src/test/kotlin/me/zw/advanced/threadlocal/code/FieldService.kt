package me.zw.advanced.threadlocal.code

import mu.KotlinLogging

class FieldService {

    private val logger = KotlinLogging.logger { }

    private var nameStore: String? = null

    fun logic(name: String): String {
        logger.info { "저장 name=$name -> nameStore=$nameStore" }
        nameStore = name;
        Thread.sleep(1000)
        logger.info { "조회 nameStore=$nameStore" }
        return nameStore!!
    }



}