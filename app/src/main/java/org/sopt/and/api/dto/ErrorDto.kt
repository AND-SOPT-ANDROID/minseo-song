package org.sopt.and.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseErrorDto(
    @SerialName("code")
    val code: String
)