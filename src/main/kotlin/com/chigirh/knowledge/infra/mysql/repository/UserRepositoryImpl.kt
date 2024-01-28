package com.chigirh.knowledge.infra.mysql.repository

import com.chigirh.knowledge.domain.model.User
import com.chigirh.knowledge.domain.model.vo.UserId
import com.chigirh.knowledge.domain.repository.UserRepository
import com.chigirh.knowledge.infra.mysql.mapper.UsersMapper
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl(
    val usersMapper: UsersMapper,
) : UserRepository {
    override fun fetchBy(userId: UserId): User? {
        return usersMapper.findByKey(userId.raw())?.let {
            User(
                userId = UserId(it.userId),
                userName = it.userName,
            )
        }
    }

}