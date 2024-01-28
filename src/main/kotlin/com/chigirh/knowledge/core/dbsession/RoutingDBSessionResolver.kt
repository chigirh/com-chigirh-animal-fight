package com.chigirh.knowledge.core.dbsession

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource
import java.util.*

class RoutingDBSessionResolver : AbstractRoutingDataSource() {

    private val map =
        DBSession.entries.toTypedArray().associateWithTo(EnumMap(DBSession::class.java)) { it.dataSourceName }

    override fun determineCurrentLookupKey(): Any? {
        val selectedDBSession = DBSessionContextHolder.get() ?: return null

        val dataSource = map[selectedDBSession]
        return dataSource
    }
}