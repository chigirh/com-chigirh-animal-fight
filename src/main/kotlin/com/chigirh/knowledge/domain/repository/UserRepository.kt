package com.chigirh.knowledge.domain.repository

import com.chigirh.knowledge.domain.model.User
import com.chigirh.knowledge.domain.model.vo.UserId

interface UserRepository {
    fun fetchBy(userId: UserId): User?
}