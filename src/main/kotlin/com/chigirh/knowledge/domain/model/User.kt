package com.chigirh.knowledge.domain.model

import com.chigirh.knowledge.domain.model.vo.InitCode
import com.chigirh.knowledge.domain.model.vo.Password
import com.chigirh.knowledge.domain.model.vo.UserId

class User(
    val userId: UserId,
    val userName: String? = null,
    val password: Password? = null,
    val isInit: Boolean = true,
    val initCode: InitCode? = null,
)