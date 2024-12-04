package org.sopt.and.api.service

import org.sopt.and.api.dto.BaseResponse
import org.sopt.and.api.dto.ResponseLoginDto
import org.sopt.and.api.dto.RequestLoginDto
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("/login")
    suspend fun postLogin(
        @Body requestLogin: RequestLoginDto
    ): BaseResponse<ResponseLoginDto>
}