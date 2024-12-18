package org.sopt.and.data.dataremote.datasourceimpl

import org.sopt.and.data.dataremote.datasource.SignUpRemoteDataSource
import org.sopt.and.data.dataremote.model.request.RequestUserDto
import org.sopt.and.data.dataremote.model.response.BaseResponse
import org.sopt.and.data.dataremote.model.response.ResponseUserDto
import org.sopt.and.data.dataremote.service.UserService
import javax.inject.Inject

class SignUpRemoteDataSourceImpl @Inject constructor(
    private val userService: UserService
) : SignUpRemoteDataSource {
    override suspend fun postUser(requestUserDto: RequestUserDto): BaseResponse<ResponseUserDto> =
        userService.postUser(requestUserDto)
}