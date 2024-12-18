package org.sopt.and.data.repositoryimpl

import org.sopt.and.data.dataremote.datasource.LoginRemoteDataSource
import org.sopt.and.data.dataremote.model.request.RequestLoginDto
import org.sopt.and.data.dataremote.model.response.BaseResponse
import org.sopt.and.data.dataremote.model.response.ResponseLoginDto
import org.sopt.and.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginRemoteDataSource: LoginRemoteDataSource
) : LoginRepository {
    override suspend fun postLogin(
        userName: String,
        userPassword: String
    ): Result<BaseResponse<ResponseLoginDto>> = runCatching {
        loginRemoteDataSource.postLogin(RequestLoginDto(userName, userPassword))
    }
}