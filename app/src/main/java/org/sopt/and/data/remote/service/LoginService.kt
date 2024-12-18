package org.sopt.and.data.remote.service

import org.sopt.and.data.remote.model.response.BaseResponse
import org.sopt.and.data.remote.model.response.ResponseLoginDto
import org.sopt.and.data.remote.model.request.RequestLoginDto
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("/login")
    suspend fun postLogin(
        @Body requestLogin: RequestLoginDto
    ): BaseResponse<ResponseLoginDto>
}