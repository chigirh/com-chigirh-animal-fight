package com.chigirh.knowledge.web.dto

open class ResponseBase(
    open val errors: List<Error>,
)

data class Error(
    val errorCode: String,
    val errorMessage: String,
)