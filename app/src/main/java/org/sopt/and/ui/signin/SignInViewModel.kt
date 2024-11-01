package org.sopt.and.ui.signin

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class SignInViewModel: ViewModel() {
    private var _userId by mutableStateOf("")
    val userId: String get() = _userId

    private var _userPassword by mutableStateOf("")
    val userPassword: String get() = _userPassword

    private var _passwordVisible by mutableStateOf(false)
    val passwordVisible: Boolean get() = _passwordVisible

    private val _snackbarMessage = MutableStateFlow<String?>(null)
    val snackbarMessage: StateFlow<String?> get() = _snackbarMessage

    fun updateUserId(id: String) {
        _userId = id
    }

    fun updateUserPassword(password: String) {
        _userPassword = password
    }

    fun togglePasswordVisibility() {
        _passwordVisible = !_passwordVisible
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