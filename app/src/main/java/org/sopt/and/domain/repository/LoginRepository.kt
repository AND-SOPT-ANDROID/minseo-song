package org.sopt.and.domain.repository

import org.sopt.and.data.dataremote.model.response.BaseResponse
import org.sopt.and.data.dataremote.model.response.ResponseLoginDto

interface LoginRepository {
    suspend fun postLogin(
        userName: String,
        userPassword: String
    ): Result<BaseResponse<ResponseLoginDto>>
}