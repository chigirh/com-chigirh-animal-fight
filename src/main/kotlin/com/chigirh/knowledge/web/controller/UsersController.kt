package com.chigirh.knowledge.web.controller

import com.chigirh.knowledge.domain.application.UserPostInput
import com.chigirh.knowledge.domain.application.UserPostUseCase
import com.chigirh.knowledge.infra.mysql.mapper.UsersMapper
import com.chigirh.knowledge.web.dto.UserGetResponse
import com.chigirh.knowledge.web.dto.UserPostRequest
import com.chigirh.knowledge.web.dto.UserPostResponse
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/v1/admin/user")
class UsersController(
    val usersMapper: UsersMapper,
    val userPostUseCase: UserPostUseCase,
) {

    @GetMapping(value = ["/{userId}"])
    fun get(@PathVariable("userId") userId: String): UserGetResponse {
        val entity = usersMapper.findByKey(userId)
        return entity?.let {
            UserGetResponse(
                userId = it.userId,
                userName = it.userName,
            )
        } ?: UserGetResponse()
    }

    @PostMapping()
    fun post(@RequestBody request: UserPostRequest): UserPostResponse {
        val input = UserPostInput(
            userId = request.userId,
            userName = request.userName,
        )

        val output = userPostUseCase(input)

        val response = UserPostResponse(output.password)
        return response
    }
}