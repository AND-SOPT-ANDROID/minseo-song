package org.sopt.and.data.remote.datasourceimpl

import org.sopt.and.data.remote.datasource.LoginRemoteDataSource
import org.sopt.and.data.remote.model.request.RequestLoginDto
import org.sopt.and.data.remote.model.response.BaseResponse
import org.sopt.and.data.remote.model.response.ResponseLoginDto
import org.sopt.and.data.remote.service.LoginService
import javax.inject.Inject

class LoginRemoteDataSourceImpl @Inject constructor(
    private val loginService: LoginService
) : LoginRemoteDataSource {
    override suspend fun postLogin(requestLoginDto: RequestLoginDto): BaseResponse<ResponseLoginDto> =
        loginService.postLogin(requestLoginDto)
}