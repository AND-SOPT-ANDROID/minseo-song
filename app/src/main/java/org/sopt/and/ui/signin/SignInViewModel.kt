package org.sopt.and.ui.signin

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.sopt.and.model.UserInfo


class SignInViewModel : ViewModel() {
    var userInfo by mutableStateOf(UserInfo("", ""))

    private val _snackbarMessage = MutableStateFlow<String?>(null)
    val snackbarMessage: StateFlow<String?> get() = _snackbarMessage

    fun updateUserInfo(id: String, password: String) {
        userInfo = userInfo.copy(userId = id, userPassWord = password)
    }

    fun performLogin(registeredUserInfo: UserInfo) {
        val loginSuccess = (userInfo == registeredUserInfo)
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