package org.sopt.and.domain.repository

import org.sopt.and.data.dataremote.model.response.BaseResponse
import org.sopt.and.data.dataremote.model.response.ResponseUserDto

interface SignUpRepository {
    suspend fun postUser(
        userName: String,
        userPassword: String,
        userHobby: String
    ):Result<BaseResponse<ResponseUserDto>>
}