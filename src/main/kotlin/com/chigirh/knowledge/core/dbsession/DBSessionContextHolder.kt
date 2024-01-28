package com.chigirh.knowledge.core.dbsession

object DBSessionContextHolder {
    private val contextHolder: ThreadLocal<DBSession> = ThreadLocal<DBSession>()

    fun set(dbSession: DBSession) = contextHolder.set(dbSession)

    fun get() = contextHolder.get()

    fun clear() = contextHolder.remove()

}