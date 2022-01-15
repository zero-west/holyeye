package me.zw.advanced.trace

import java.util.*

class TraceId {

    val id: String

    val level: Int

    constructor() {
        id = createId()
        level = 0
    }

    constructor(id: String, level: Int) {
        this.id = id
        this.level = level
    }

    private fun createId(): String = UUID.randomUUID().toString().substring(0, 8)

    fun createNextId() = TraceId(id, level + 1)

    fun createPreviousId() = TraceId(id, level - 1)

    fun isFirstLevel() = (level == 0)

}