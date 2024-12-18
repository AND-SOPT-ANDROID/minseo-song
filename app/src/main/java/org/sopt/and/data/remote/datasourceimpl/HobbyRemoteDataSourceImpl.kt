package org.sopt.and.data.remote.datasourceimpl

import org.sopt.and.data.remote.datasource.HobbyRemoteDataSource
import org.sopt.and.data.remote.model.response.BaseResponse
import org.sopt.and.data.remote.model.response.ResponseHobbyDto
import org.sopt.and.data.remote.service.UserService
import javax.inject.Inject

class HobbyRemoteDataSourceImpl @Inject constructor(
    private val userService: UserService
) : HobbyRemoteDataSource {
    override suspend fun getMyHobby(token: String): BaseResponse<ResponseHobbyDto> =
        userService.getMyHobby(token)
}