package com.chigirh.knowledge.common.const


class DataSourceConfigConst{
    companion object {
        const val READ_WRITE_DATASOURCE = "readWriteDataSource"
        const val READ_WRITE_DATASOURCE_PROPERTIES = "readWriteDataSourceProperties"

        const val READ_ONLY_DATASOURCE = "readOnlyDataSource"
        const val READ_ONLY_DATASOURCE_PROPERTIES = "readOnlyDataSourceProperties"
    }
}

class SqlSessionFactoryConfigConst{
    companion object {
        const val SQL_SESSION_FACTORY = "sqlSessionFactory"
        const val ROUTING_DB_SESSION_RESOLVER = "routingDBSessionResolver"
        const val TX_MANAGER = "txManager"
    }
}