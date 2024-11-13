package org.sopt.and.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseUserSuccessDto(
    @SerialName("result")
    val result: UserData
)

@Serializable
data class UserData(
    @SerialName("no")
    val no: Int
)

@Serializable
data class RequestUserDto(
    @SerialName("username")
    val username: String,
    @SerialName("password")
    val password: String,
    @SerialName("hobby")
    val hobby: String
)