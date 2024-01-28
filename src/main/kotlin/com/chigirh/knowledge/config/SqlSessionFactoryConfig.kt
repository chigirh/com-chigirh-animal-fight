package com.chigirh.knowledge.config

import com.chigirh.knowledge.common.const.DataSourceConfigConst.Companion.READ_ONLY_DATASOURCE
import com.chigirh.knowledge.common.const.DataSourceConfigConst.Companion.READ_WRITE_DATASOURCE
import com.chigirh.knowledge.common.const.SqlSessionFactoryConfigConst.Companion.ROUTING_DB_SESSION_RESOLVER
import com.chigirh.knowledge.common.const.SqlSessionFactoryConfigConst.Companion.SQL_SESSION_FACTORY
import com.chigirh.knowledge.common.const.SqlSessionFactoryConfigConst.Companion.TX_MANAGER
import com.chigirh.knowledge.core.dbsession.RoutingDBSessionResolver
import org.apache.ibatis.session.SqlSessionFactory
import org.mybatis.spring.SqlSessionFactoryBean
import org.mybatis.spring.annotation.MapperScan
import org.mybatis.spring.boot.autoconfigure.MybatisProperties
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.core.io.support.PathMatchingResourcePatternResolver
import org.springframework.jdbc.support.JdbcTransactionManager
import org.springframework.transaction.PlatformTransactionManager
import javax.sql.DataSource


@Configuration
@MapperScan(
    basePackages = ["com.chigirh.knowledge.infra.mysql.mapper"],
    sqlSessionFactoryRef = SQL_SESSION_FACTORY
)
class SqlSessionFactoryConfig {

    @Bean(name = [ROUTING_DB_SESSION_RESOLVER])
    fun routingDBSessionResolver(
        @Qualifier(READ_WRITE_DATASOURCE) readWriteDataSource: DataSource,
        @Qualifier(READ_ONLY_DATASOURCE) readOnlyDataSource: DataSource,
    ): RoutingDBSessionResolver {
        val bean = RoutingDBSessionResolver()

        val dataSources: MutableMap<Any, Any> = LinkedHashMap()
        dataSources[READ_WRITE_DATASOURCE] = readWriteDataSource
        dataSources[READ_ONLY_DATASOURCE] = readOnlyDataSource

        bean.setTargetDataSources(dataSources)
        bean.setDefaultTargetDataSource(readWriteDataSource)

        return bean
    }

    @Bean("mybatisProperties")
    fun mybatisProperties() = MybatisProperties()

    @Bean(name = [SQL_SESSION_FACTORY])
    fun sqlSessionFactory(
        @Qualifier(ROUTING_DB_SESSION_RESOLVER) dataSource: DataSource,
        @Qualifier("mybatisProperties") mybatisProperties: MybatisProperties,
    ): SqlSessionFactory {
        val sqlSessionFactoryBean = SqlSessionFactoryBean()
        sqlSessionFactoryBean.setDataSource(dataSource)
        sqlSessionFactoryBean.vfs = SpringBootVFS::class.java

        // mybatis
        val configuration = org.apache.ibatis.session.Configuration()
        configuration.isMapUnderscoreToCamelCase = mybatisProperties.configuration.mapUnderscoreToCamelCase

        // mapper location
        sqlSessionFactoryBean.setMapperLocations(
            *PathMatchingResourcePatternResolver().getResources(
                mybatisProperties.mapperLocations[0]
            )
        )
        sqlSessionFactoryBean.setConfiguration(configuration)
        return sqlSessionFactoryBean.getObject()!!
    }

    @Primary
    @Bean(name = [TX_MANAGER])
    fun txManager(
        @Qualifier(ROUTING_DB_SESSION_RESOLVER) dataSource: DataSource,
    ): PlatformTransactionManager {
        return JdbcTransactionManager(dataSource)
    }
}