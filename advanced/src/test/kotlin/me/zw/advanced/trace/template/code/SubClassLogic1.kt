package me.zw.advanced.trace.template.code

class SubClassLogic1 : AbstractTemplate() {

    override fun call() {
        logger.info { "비즈니스 로직 1 실행" }
    }
}