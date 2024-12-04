package org.sopt.and.api.service

import org.sopt.and.api.dto.response.BaseResponse
import org.sopt.and.api.dto.response.ResponseLoginDto
import org.sopt.and.api.dto.request.RequestLoginDto
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("/login")
    suspend fun postLogin(
        @Body requestLogin: RequestLoginDto
    ): BaseResponse<ResponseLoginDto>
}