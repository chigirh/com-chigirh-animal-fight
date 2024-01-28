package com.chigirh.knowledge.domain.repository

import com.chigirh.knowledge.domain.model.User
import com.chigirh.knowledge.domain.model.vo.UserId

interface UserSecureRepository {
    fun fetchBy(userId: UserId): User?

    fun insertUser(user: User)
}