package com.chigirh.knowledge.domain.error

open class BusinessException(
    override val message: String?,
    open val errorCode: ErrorCode,
) : RuntimeException()