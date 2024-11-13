package org.sopt.and.api.service

import okhttp3.ResponseBody
import org.sopt.and.api.dto.RequestUserDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("/user")
    fun postUser(
        @Body requestUser: RequestUserDto
    ): Call<ResponseBody>
}