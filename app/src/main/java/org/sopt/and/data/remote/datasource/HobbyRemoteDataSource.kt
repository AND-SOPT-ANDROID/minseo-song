package org.sopt.and.data.remote.datasource

import org.sopt.and.data.remote.model.response.BaseResponse
import org.sopt.and.data.remote.model.response.ResponseHobbyDto

interface HobbyRemoteDataSource {
    suspend fun getMyHobby(token: String): BaseResponse<ResponseHobbyDto>
}