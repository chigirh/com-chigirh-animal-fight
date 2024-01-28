package com.chigirh.knowledge.domain.error

open class ValidException(
    override val cause: Throwable? = null,
    override val message: String?,
    override val errorCode: ErrorCode = ErrorCode.VALIDATE,
) : BusinessException(
    message = message,
    errorCode = errorCode,
)