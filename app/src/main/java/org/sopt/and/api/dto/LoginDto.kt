package org.sopt.and.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ResponseLoginDto(
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