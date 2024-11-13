package org.sopt.and.api.service

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface HobbyService {
    @GET("/user/my-hobby")
    fun getMyHobby(
        @Header("token") token: String
    ): Call<ResponseBody>
}