package org.sopt.and.presentation.ui.signin

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.sopt.and.domain.usecase.LoginUseCase
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    var userId = MutableStateFlow("")
        private set
    var userPassWord = MutableStateFlow("")
        private set

    private val _snackbarMessage = MutableStateFlow<String?>(null)
    val snackbarMessage: StateFlow<String?> get() = _snackbarMessage

    private var sharedPreferences: SharedPreferences? = null

    fun initializePreferences(context: Context) {
        sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    }

    private fun saveToken(token: String) {
        sharedPreferences?.edit()?.putString("token", token)?.apply()
    }

    fun updateUserId(id: String) {
        userId.value = id
    }

    fun updateUserPassword(password: String) {
        userPassWord.value = password
    }

    fun loginUser() {
        viewModelScope.launch {
            val result = loginUseCase.invoke(userId.value, userPassWord.value)
            result.onSuccess { response ->
                val token = response.result.token
                saveToken(token)
                _snackbarMessage.value = "로그인 성공!"
            }.onFailure { error ->
                _snackbarMessage.value = when (error) {
                    is retrofit2.HttpException -> "서버 오류: ${error.code()} ${error.message()}"
                    is java.net.UnknownHostException -> "네트워크 연결 오류"
                    else -> "${error.message}"
                }
            }
        }
    }

    fun clearSnackbarMessage() {
        _snackbarMessage.value = null
    }
}

