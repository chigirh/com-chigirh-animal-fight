package com.chigirh.knowledge.infra.mysql.repository

import com.chigirh.knowledge.domain.model.User
import com.chigirh.knowledge.domain.model.vo.InitCode
import com.chigirh.knowledge.domain.model.vo.Password
import com.chigirh.knowledge.domain.model.vo.UserId
import com.chigirh.knowledge.domain.repository.UserSecureRepository
import com.chigirh.knowledge.infra.mysql.entity.UsersEntity
import com.chigirh.knowledge.infra.mysql.entity.UsersSecEntity
import com.chigirh.knowledge.infra.mysql.mapper.UsersMapper
import com.chigirh.knowledge.infra.mysql.mapper.UsersSecMapper
import org.springframework.stereotype.Repository

@Repository
class UserSecureRepositoryImpl(
    val usersMapper: UsersMapper,
    val usersSecMapper: UsersSecMapper,
) : UserSecureRepository {
    override fun fetchBy(userId: UserId): User? {
        return usersSecMapper.findByKey(userId.raw())?.let {
            User(
                userId = UserId(it.userId),
                password = Password(it.password),
                isInit = it.isInit,
                initCode = if (it.initCode != null) InitCode(it.initCode) else null,
            )
        }
    }

    override fun insertUser(user: User) {
        val userEntity = UsersEntity(
            userId = user.userId.raw(),
            userName = user.userName!!,
        )
        usersMapper.insert(userEntity)

        val usersSecEntity = UsersSecEntity(
            userId = user.userId.raw(),
            password = user.password!!.raw(),
            isInit = user.isInit,
            initCode = null,
        )
        usersSecMapper.insert(usersSecEntity)
    }
}