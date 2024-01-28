package com.chigirh.knowledge.domain.application

import com.chigirh.knowledge.common.const.UserMessageConst
import com.chigirh.knowledge.core.message.MessageSourceEn
import com.chigirh.knowledge.domain.error.ConflictException
import com.chigirh.knowledge.domain.model.User
import com.chigirh.knowledge.domain.model.vo.Password
import com.chigirh.knowledge.domain.model.vo.UserId
import com.chigirh.knowledge.domain.repository.UserRepository
import com.chigirh.knowledge.domain.repository.UserSecureRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class UserPostUseCase(
    val messageSourceEn: MessageSourceEn,
    val userRepository: UserRepository,
    val userSecureRepository: UserSecureRepository,
) {
    @Transactional
    operator fun invoke(input: UserPostInput): UserPostOutput {
        val userId = UserId(input.userId)

        userRepository.fetchBy(userId)?.let {
            val message = messageSourceEn.message(UserMessageConst.ALREADY_EXISTS, userId.raw())
            log.warn(message)
            throw ConflictException(message = message)
        }

        val initPassword = Password(UUID.randomUUID().toString().substring(0, 10))
        val initHashPassword = initPassword.hash()

        val user = User(
            userId = userId,
            userName = input.userName,
            password = initHashPassword,
            isInit = false,
        )

        userSecureRepository.insertUser(user)

        return UserPostOutput(initPassword.raw())
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(UserPostUseCase::class.java)
    }
}

data class UserPostInput(
    val userId: String,
    val userName: String,
)

data class UserPostOutput(
    val password: String,
)