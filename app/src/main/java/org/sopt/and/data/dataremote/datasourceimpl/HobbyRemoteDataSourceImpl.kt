package org.sopt.and.data.dataremote.datasourceimpl

import org.sopt.and.data.dataremote.datasource.HobbyRemoteDataSource
import org.sopt.and.data.dataremote.model.response.BaseResponse
import org.sopt.and.data.dataremote.model.response.ResponseHobbyDto
import org.sopt.and.data.dataremote.service.UserService
import javax.inject.Inject

class HobbyRemoteDataSourceImpl @Inject constructor(
    private val userService: UserService
) : HobbyRemoteDataSource {
    override suspend fun getMyHobby(token: String): BaseResponse<ResponseHobbyDto> =
        userService.getMyHobby(token)
}