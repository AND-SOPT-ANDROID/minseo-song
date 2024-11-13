package org.sopt.and.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseHobbySuccessDto(
    @SerialName("result")
    val result: HobbyData
)

@Serializable
data class HobbyData(
    @SerialName("hobby")
    val hobby: String
)