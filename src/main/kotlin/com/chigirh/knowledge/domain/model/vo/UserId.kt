package com.chigirh.knowledge.domain.model.vo

data class UserId(
    override val raw: String,
) : ValueObject<String>(
    raw = raw,
)
