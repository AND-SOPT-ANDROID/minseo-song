package org.sopt.and.ui.signin

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SignInViewModel : ViewModel() {
    var userId by mutableStateOf("")
    var userPassword by mutableStateOf("")
    var passwordVisible by mutableStateOf(false)

    private val _snackbarMessage = MutableStateFlow<String?>(null)
    val snackbarMessage: StateFlow<String?> get() = _snackbarMessage

    fun updateUserId(id: String) {
        userId = id
    }

    fun updateUserPassword(password: String) {
        userPassword = password
    }

    fun togglePasswordVisibility() {
        passwordVisible = !passwordVisible
    }

    fun performLogin(registeredId: String, registeredPassword: String) {
        val loginSuccess = (userId == registeredId && userPassword == registeredPassword && userId.isNotBlank() && userPassword.isNotBlank())
        _snackbarMessage.value = if (loginSuccess) {
            "로그인 성공"
        } else {
            "로그인 실패"
        }
    }

    fun clearSnackbarMessage() {
        _snackbarMessage.value = null
    }
}