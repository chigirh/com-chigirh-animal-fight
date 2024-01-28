package com.chigirh.knowledge.domain.model.vo

import com.chigirh.knowledge.common.util.MessageDigestUtil
import java.util.*

class InitCode(
    override val raw: String = MessageDigestUtil.sha256(UUID.randomUUID().toString()).substring(0, 10),
) : ValueObject<String>(
    raw = raw,
)