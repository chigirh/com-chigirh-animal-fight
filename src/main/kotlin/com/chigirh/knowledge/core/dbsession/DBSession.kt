package com.chigirh.knowledge.core.dbsession

import com.chigirh.knowledge.common.const.DataSourceConfigConst

enum class DBSession(
    val dataSourceName: String,
) {
    READ_WRITE(DataSourceConfigConst.READ_WRITE_DATASOURCE),
    READ_ONLY(DataSourceConfigConst.READ_WRITE_DATASOURCE),
}