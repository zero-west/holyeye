package me.zw.advanced.threadlocal.code

import mu.KotlinLogging

class ThreadLocalService {

    private val logger = KotlinLogging.logger { }

    private var nameStore = ThreadLocal<String?>()

    fun logic(name: String): String {
        logger.info { "저장 name=$name -> nameStore=${nameStore.get()}" }
        nameStore.set(name)
        Thread.sleep(1000)
        logger.info { "조회 nameStore=${nameStore.get()}" }
        return nameStore.get()!!
    }


}