package org.sopt.and.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseLoginSuccessDto(
    @SerialName("result")
    val result: LoginData
)

@Serializable
data class LoginData(
    @SerialName("token")
    val token: String
)

@Serializable
data class RequestLoginDto(
    @SerialName("username")
    val username: String,
    @SerialName("password")
    val password: String
)