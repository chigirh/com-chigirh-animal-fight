package com.chigirh.knowledge.domain.error

open class ConflictException(
    override val cause: Throwable? = null,
    override val message: String?,
    override val errorCode: ErrorCode = ErrorCode.CONFLICT,
) : BusinessException(
    message = message,
    errorCode = errorCode,
)