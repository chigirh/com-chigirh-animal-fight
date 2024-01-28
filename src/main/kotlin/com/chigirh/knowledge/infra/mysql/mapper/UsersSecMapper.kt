package com.chigirh.knowledge.infra.mysql.mapper

import com.chigirh.knowledge.infra.mysql.entity.UsersSecEntity
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param

@Mapper
interface UsersSecMapper {
    fun insert(@Param("entity") entity: UsersSecEntity)
    fun findByKey(@Param("userId") userId: String): UsersSecEntity?
}