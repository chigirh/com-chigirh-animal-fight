package com.chigirh.knowledge.web.dto

data class UserGetResponse(
    val userId: String? = null,
    val userName: String? = null,
) : ResponseBase(
    errors = emptyList()
)
