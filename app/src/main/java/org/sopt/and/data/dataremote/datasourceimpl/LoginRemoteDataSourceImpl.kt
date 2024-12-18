package org.sopt.and.data.dataremote.datasourceimpl

import org.sopt.and.data.dataremote.datasource.LoginRemoteDataSource
import org.sopt.and.data.dataremote.model.request.RequestLoginDto
import org.sopt.and.data.dataremote.model.response.BaseResponse
import org.sopt.and.data.dataremote.model.response.ResponseLoginDto
import org.sopt.and.data.dataremote.service.LoginService
import javax.inject.Inject

class LoginRemoteDataSourceImpl @Inject constructor(
    private val loginService: LoginService
) : LoginRemoteDataSource {
    override suspend fun postLogin(requestLoginDto: RequestLoginDto): BaseResponse<ResponseLoginDto> =
        loginService.postLogin(requestLoginDto)
}