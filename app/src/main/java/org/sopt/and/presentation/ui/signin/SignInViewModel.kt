package org.sopt.and.presentation.ui.signin

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.and.R
import org.sopt.and.domain.usecase.LoginUseCase
import org.sopt.and.presentation.core.BaseViewModel
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : BaseViewModel<SignInState, SignInSideEffect, SignInEvent>() {

    private var sharedPreferences: SharedPreferences? = null

    override fun createInitialState(): SignInState = SignInState()

    fun initializePreferences(context: Context) {
        sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    }

    override suspend fun handleEvent(event: SignInEvent) {
        when (event) {
            is SignInEvent.UserIdChanged -> setState { copy(userId = event.userId) }
            is SignInEvent.UserPasswordChanged -> setState { copy(userPassWord = event.password) }
            is SignInEvent.SignInClicked -> loginUser()
        }
    }

    private fun loginUser() {
        setState { copy(isLoading = true) }
        viewModelScope.launch {
            val state = uiState.value
            val result = loginUseCase.invoke(state.userId, state.userPassWord)
            result.onSuccess { response ->
                saveToken(response.result.token)
                setState { copy(isLoading = false) }
                setSideEffect { SignInSideEffect.ShowSnackBar("로그인 성공!") }
            }.onFailure { error ->
                val message = when (error) {
                    is HttpException -> "서버 오류: ${error.code()} ${error.message()}"
                    is java.net.UnknownHostException -> "네트워크 연결 오류"
                    else -> error.message ?: "알 수 없는 오류"
                }
                setState { copy(isLoading = false) }
                setSideEffect { SignInSideEffect.ShowSnackBar(message) }
            }
        }
    }

    private fun saveToken(token: String) {
        sharedPreferences?.edit()?.putString("token", token)?.apply()
    }
}
