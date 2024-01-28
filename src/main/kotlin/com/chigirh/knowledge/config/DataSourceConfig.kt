package com.chigirh.knowledge.config

import com.chigirh.knowledge.common.const.DataSourceConfigConst.Companion.READ_ONLY_DATASOURCE
import com.chigirh.knowledge.common.const.DataSourceConfigConst.Companion.READ_ONLY_DATASOURCE_PROPERTIES
import com.chigirh.knowledge.common.const.DataSourceConfigConst.Companion.READ_WRITE_DATASOURCE
import com.chigirh.knowledge.common.const.DataSourceConfigConst.Companion.READ_WRITE_DATASOURCE_PROPERTIES
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class DataSourceConfig {
    @Bean(name = [READ_WRITE_DATASOURCE_PROPERTIES])
    @ConfigurationProperties(prefix = "spring.datasource.writer")
    fun readWriteDataSourceProperties(): DataSourceProperties = DataSourceProperties()

    @Bean(name = [READ_WRITE_DATASOURCE])
    fun readWriteDataSource(
        @Qualifier(READ_WRITE_DATASOURCE_PROPERTIES) properties: DataSourceProperties
    ) = properties.initializeDataSourceBuilder().build()

    @Bean(name = [READ_ONLY_DATASOURCE_PROPERTIES])
    @ConfigurationProperties(prefix = "spring.datasource.reader")
    fun readOnlyDataSourceProperties() = DataSourceProperties()

    @Bean(name = [READ_ONLY_DATASOURCE])
    fun readOnlyDataSource(
        @Qualifier(READ_WRITE_DATASOURCE_PROPERTIES) properties: DataSourceProperties
    ) = properties.initializeDataSourceBuilder().build()


}