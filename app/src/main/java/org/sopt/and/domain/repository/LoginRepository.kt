package org.sopt.and.domain.repository

import org.sopt.and.domain.model.Token

interface LoginRepository {
    suspend fun postLogin(
        userName: String,
        userPassword: String
    ): Result<Token>
}