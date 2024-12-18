package org.sopt.and.presentation.ui.signup

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.and.domain.usecase.SignUpUseCase
import org.sopt.and.presentation.core.BaseViewModel
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
) : BaseViewModel<SignUpState, SignUpSideEffect, SignUpEvent>() {

    override fun createInitialState(): SignUpState = SignUpState()

    override suspend fun handleEvent(event: SignUpEvent) {
        when (event) {
            is SignUpEvent.UserIdChanged -> setState { copy(userId = event.userId) }
            is SignUpEvent.UserPasswordChanged -> setState { copy(userPassWord = event.userPassword) }
            is SignUpEvent.UserHobbyChanged -> setState { copy(userHobby = event.userHobby) }
            is SignUpEvent.SignUpClicked -> signUpUser()
        }
    }

    private fun signUpUser() {
        setState { copy(isLoading = true) }
        val currentState = uiState.value
        viewModelScope.launch {
            val result = signUpUseCase(currentState.userId, currentState.userPassWord, currentState.userHobby)
            result.onSuccess { response ->
                setState { copy(isLoading = false) }
                setSideEffect { SignUpSideEffect.ShowToast("회원가입 성공! 유저 ID: ${response.userNumber}") }
                setSideEffect { SignUpSideEffect.NavigateToSignIn }
            }.onFailure { error ->
                val errorMessage = when (error) {
                    is HttpException -> "서버 오류: ${error.code()} ${error.message()}"
                    is java.net.UnknownHostException -> "네트워크 연결 오류"
                    else -> error.message ?: "알 수 없는 오류"
                }
                setState { copy(isLoading = false) }
                setSideEffect { SignUpSideEffect.ShowToast(errorMessage) }
            }
        }
    }
}
