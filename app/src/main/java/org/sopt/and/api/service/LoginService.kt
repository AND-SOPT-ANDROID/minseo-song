package org.sopt.and.api.service

import okhttp3.ResponseBody
import org.sopt.and.api.dto.RequestLoginDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("/login")
    fun postLogin(
        @Body requestLogin: RequestLoginDto
    ): Call<ResponseBody>
}