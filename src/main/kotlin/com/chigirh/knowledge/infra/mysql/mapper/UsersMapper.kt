package com.chigirh.knowledge.infra.mysql.mapper

import com.chigirh.knowledge.infra.mysql.entity.UsersEntity
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param

@Mapper
interface UsersMapper {
    fun insert(@Param("entity") entity: UsersEntity)
    fun findByKey(@Param("userId") userId: String): UsersEntity?
}