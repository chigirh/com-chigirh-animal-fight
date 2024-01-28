package com.chigirh.knowledge.domain.error

enum class ErrorCode(
    val code: String,
) {
    CONFLICT("409"),
    VALIDATE("800"),
    SYSTEM("900"),
}