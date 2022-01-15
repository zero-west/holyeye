package me.zw.advanced.trace.template.code

class SubClassLogic2 : AbstractTemplate() {

    override fun call() {
        logger.info { "비즈니스 로직2 실행" }
    }
}