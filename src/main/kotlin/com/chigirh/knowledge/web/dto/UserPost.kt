package com.chigirh.knowledge.web.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class UserPostRequest(
    @JsonProperty("user_id")
    val userId: String,
    @JsonProperty("user_name")
    val userName: String,
)

data class UserPostResponse(
    @JsonProperty("password")
    val password: String,
)