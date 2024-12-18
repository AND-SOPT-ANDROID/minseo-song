package org.sopt.and.domain.repository

import org.sopt.and.domain.model.User

interface SignUpRepository {
    suspend fun postUser(
        userName: String,
        userPassword: String,
        userHobby: String
    ): Result<User>
}