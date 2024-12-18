package org.sopt.and.data.dataremote.datasource

import org.sopt.and.data.dataremote.model.response.BaseResponse
import org.sopt.and.data.dataremote.model.response.ResponseHobbyDto

interface HobbyRemoteDataSource {
    suspend fun getMyHobby(token: String):BaseResponse<ResponseHobbyDto>
}