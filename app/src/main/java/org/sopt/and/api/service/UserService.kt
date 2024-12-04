package org.sopt.and.api.service

import org.sopt.and.api.dto.request.RequestUserDto
import org.sopt.and.api.dto.response.BaseResponse
import org.sopt.and.api.dto.response.ResponseHobbyDto
import org.sopt.and.api.dto.response.ResponseUserDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface UserService {
    @POST("/user")
    suspend fun postUser(
        @Body requestUser: RequestUserDto
    ): BaseResponse<ResponseUserDto>


    @GET("/user/my-hobby")
    suspend fun getMyHobby(
        @Header("token") token: String
    ): BaseResponse<ResponseHobbyDto>
}