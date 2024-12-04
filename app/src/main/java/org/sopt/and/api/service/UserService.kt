package org.sopt.and.api.service

import org.sopt.and.api.dto.BaseResponse
import org.sopt.and.api.dto.RequestUserDto
import org.sopt.and.api.dto.ResponseUserDto
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("/user")
    suspend fun postUser(
        @Body requestUser: RequestUserDto
    ): BaseResponse<ResponseUserDto>
}