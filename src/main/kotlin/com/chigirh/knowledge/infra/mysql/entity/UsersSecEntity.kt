package com.chigirh.knowledge.infra.mysql.entity

data class UsersSecEntity(
    val userId: String,
    val password: String,
    val isInit: Boolean,
    val initCode: String?,
)
