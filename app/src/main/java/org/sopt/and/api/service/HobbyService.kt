package org.sopt.and.api.service

import org.sopt.and.api.dto.BaseResponse
import org.sopt.and.api.dto.ResponseHobbyDto
import retrofit2.http.GET
import retrofit2.http.Header

interface HobbyService {
    @GET("/user/my-hobby")
    suspend fun getMyHobby(
        @Header("token") token: String
    ): BaseResponse<ResponseHobbyDto>
}