package org.sopt.and.domain.repository

import org.sopt.and.data.dataremote.model.response.BaseResponse
import org.sopt.and.data.dataremote.model.response.ResponseHobbyDto

interface HobbyRepository {
    suspend fun getMyHobby(
        token: String
    ): Result<BaseResponse<ResponseHobbyDto>>
}