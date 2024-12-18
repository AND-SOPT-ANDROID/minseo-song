package org.sopt.and.domain.usecase

import org.sopt.and.domain.model.User
import org.sopt.and.domain.repository.SignUpRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SignUpUseCase @Inject constructor(
    private val signUpRepository: SignUpRepository
) {
    suspend operator fun invoke(
        userName: String,
        userPassword: String,
        userHobby: String
    ): Result<User> {
        return signUpRepository.postUser(userName, userPassword, userHobby)
    }
}