package org.sopt.and.data.remote.datasourceimpl

import org.sopt.and.data.remote.datasource.SignUpRemoteDataSource
import org.sopt.and.data.remote.model.request.RequestUserDto
import org.sopt.and.data.remote.model.response.BaseResponse
import org.sopt.and.data.remote.model.response.ResponseUserDto
import org.sopt.and.data.remote.service.UserService
import javax.inject.Inject

class SignUpRemoteDataSourceImpl @Inject constructor(
    private val userService: UserService
) : SignUpRemoteDataSource {
    override suspend fun postUser(requestUserDto: RequestUserDto): BaseResponse<ResponseUserDto> =
        userService.postUser(requestUserDto)
}