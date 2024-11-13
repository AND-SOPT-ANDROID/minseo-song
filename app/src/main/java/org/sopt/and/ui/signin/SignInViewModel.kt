package org.sopt.and.ui.signin

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.sopt.and.model.UserInfo


class SignInViewModel : ViewModel() {
    var userInfo by mutableStateOf(UserInfo("", ""))
    var sharedPreferences: SharedPreferences? = null

    private val _snackbarMessage = MutableStateFlow<String?>(null)
    val snackbarMessage: StateFlow<String?> get() = _snackbarMessage

    fun initializePreferences(context: Context) {
        sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    }

    fun updateUserInfo(id: String, password: String) {
        userInfo = UserInfo(userId = id, userPassWord = password)
    }

    fun performLogin() {
        val savedUserId = sharedPreferences?.getString("userId", "") ?: ""
        val savedUserPassword = sharedPreferences?.getString("userPassWord", "") ?: ""
        val loginSuccess =
            (userInfo.userId == savedUserId && userInfo.userPassWord == savedUserPassword)

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