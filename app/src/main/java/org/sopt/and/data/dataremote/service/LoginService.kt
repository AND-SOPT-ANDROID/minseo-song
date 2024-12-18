package org.sopt.and.data.dataremote.service

import org.sopt.and.data.dataremote.model.response.BaseResponse
import org.sopt.and.data.dataremote.model.response.ResponseLoginDto
import org.sopt.and.data.dataremote.model.request.RequestLoginDto
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("/login")
    suspend fun postLogin(
        @Body requestLogin: RequestLoginDto
    ): BaseResponse<ResponseLoginDto>
}