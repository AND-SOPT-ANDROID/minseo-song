package org.sopt.and.domain.usecase

import org.sopt.and.domain.model.Token
import org.sopt.and.domain.repository.LoginRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) {
    suspend operator fun invoke(
        userName: String,
        userPassword: String
    ): Result<Token> {
        return loginRepository.postLogin(userName, userPassword)
    }
}