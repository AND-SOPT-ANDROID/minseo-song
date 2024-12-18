package org.sopt.and.domain.usecase

import org.sopt.and.data.dataremote.model.response.BaseResponse
import org.sopt.and.data.dataremote.model.response.ResponseLoginDto
import org.sopt.and.domain.repository.LoginRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository
){
    suspend operator fun invoke(userName: String, userPassword: String): Result<BaseResponse<ResponseLoginDto>> {
        return loginRepository.postLogin(userName, userPassword)
    }
}