package org.sopt.and.data.repositoryimpl

import org.sopt.and.data.mapper.toDomain
import org.sopt.and.data.remote.datasource.SignUpRemoteDataSource
import org.sopt.and.data.remote.model.request.RequestUserDto
import org.sopt.and.domain.model.User
import org.sopt.and.domain.repository.SignUpRepository
import javax.inject.Inject

class SignUpRepositoryImpl @Inject constructor(
    private val signUpRemoteDataSource: SignUpRemoteDataSource
) : SignUpRepository {
    override suspend fun postUser(
        userName: String,
        userPassword: String,
        userHobby: String
    ): Result<User> = runCatching {
        val response = signUpRemoteDataSource.postUser(RequestUserDto(userName, userPassword, userHobby))
        response.toDomain()
    }
}
