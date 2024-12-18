package org.sopt.and.data.remote.service

import org.sopt.and.data.remote.model.request.RequestUserDto
import org.sopt.and.data.remote.model.response.BaseResponse
import org.sopt.and.data.remote.model.response.ResponseHobbyDto
import org.sopt.and.data.remote.model.response.ResponseUserDto
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