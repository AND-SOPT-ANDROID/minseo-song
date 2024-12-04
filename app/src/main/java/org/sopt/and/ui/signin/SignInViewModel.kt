package org.sopt.and.ui.signin

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.sopt.and.api.ServicePool
import org.sopt.and.api.dto.request.RequestLoginDto


class SignInViewModel : ViewModel() {
    private val loginService = ServicePool.loginService

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
            try {
                val response = loginService.postLogin(RequestLoginDto(userId.value, userPassWord.value))
                val token = response.result.token
                saveToken(token)
                _snackbarMessage.value = "로그인 성공!"
            } catch (e: Exception) {
                _snackbarMessage.value = "오류 발생: ${e.message}"
            }
        }
    }

    fun clearSnackbarMessage() {
        _snackbarMessage.value = null
    }
}

