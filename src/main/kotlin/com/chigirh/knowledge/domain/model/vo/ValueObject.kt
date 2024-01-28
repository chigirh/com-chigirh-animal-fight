package com.chigirh.knowledge.domain.model.vo

import com.chigirh.knowledge.domain.error.ValidException

open class ValueObject<E>(
    protected open val raw: E
) {
    init {
        if (!validate(raw)) throw ValidException(message = "validation error")
    }

    fun raw() = raw

    fun validate(raw: E) = true
}
