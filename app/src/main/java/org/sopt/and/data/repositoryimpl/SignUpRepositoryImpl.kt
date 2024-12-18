package org.sopt.and.data.repositoryimpl

import org.sopt.and.data.dataremote.datasource.SignUpRemoteDataSource
import org.sopt.and.data.dataremote.model.request.RequestUserDto
import org.sopt.and.data.dataremote.model.response.BaseResponse
import org.sopt.and.data.dataremote.model.response.ResponseUserDto
import org.sopt.and.domain.repository.SignUpRepository
import javax.inject.Inject

class SignUpRepositoryImpl @Inject constructor(
    private val signUpRemoteDataSource: SignUpRemoteDataSource
) : SignUpRepository {
    override suspend fun postUser(
        userName: String,
        userPassword: String,
        userHobby: String
    ): Result<BaseResponse<ResponseUserDto>> = runCatching {
        signUpRemoteDataSource.postUser(RequestUserDto(userName, userPassword, userHobby))
    }
}