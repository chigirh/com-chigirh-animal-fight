package com.chigirh.knowledge.domain.model.vo

import com.chigirh.knowledge.common.util.MessageDigestUtil

class Password(
    override val raw: String,
) : ValueObject<String>(
    raw = raw,
) {
    fun hash() = Password(MessageDigestUtil.sha256(raw))
}