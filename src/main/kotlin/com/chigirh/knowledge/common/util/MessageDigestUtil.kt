package com.chigirh.knowledge.common.util

import com.chigirh.knowledge.domain.error.KnowledgeSystemError
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


object MessageDigestUtil {
    fun sha256(rawText: String): String {
        return try {
            val sha256: MessageDigest = MessageDigest.getInstance("SHA-256")
            val sha256Byte: ByteArray = sha256.digest(rawText.toByteArray())
            sha256Byte.fold("") { str, it -> str + "%02x".format(it) }

        } catch (e: NoSuchAlgorithmException) {
            throw KnowledgeSystemError(message = e.message, cause = e)
        }
    }
}