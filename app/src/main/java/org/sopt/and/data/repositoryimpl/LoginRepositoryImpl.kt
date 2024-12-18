package org.sopt.and.data.repositoryimpl

import org.sopt.and.data.mapper.toDomain
import org.sopt.and.data.remote.datasource.LoginRemoteDataSource
import org.sopt.and.data.remote.model.request.RequestLoginDto
import org.sopt.and.domain.model.Token
import org.sopt.and.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginRemoteDataSource: LoginRemoteDataSource
) : LoginRepository {
    override suspend fun postLogin(
        userName: String,
        userPassword: String
    ): Result<Token> = runCatching {
        val response = loginRemoteDataSource.postLogin(RequestLoginDto(userName, userPassword))
        response.toDomain()
    }
}