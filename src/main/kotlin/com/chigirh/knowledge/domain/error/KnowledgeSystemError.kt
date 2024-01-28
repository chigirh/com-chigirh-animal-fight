package com.chigirh.knowledge.domain.error

data class KnowledgeSystemError(
    override val cause: Throwable,
    override val message: String?,
    val errorCode: ErrorCode = ErrorCode.SYSTEM,
) : RuntimeException()
