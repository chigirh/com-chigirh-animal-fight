package com.chigirh.knowledge.core.message

import org.springframework.context.MessageSource
import org.springframework.stereotype.Component
import java.util.*

@Component
class MessageSourceEn(
    private val messageSource: MessageSource,
) {
    fun message(
        code: String,
        vararg args: String,
    ): String {
        return messageSource.getMessage(code, args, Locale.ENGLISH)
    }
}