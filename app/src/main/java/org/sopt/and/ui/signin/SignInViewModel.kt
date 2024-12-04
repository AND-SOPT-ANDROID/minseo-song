package org.sopt.and.ui.signin

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.sopt.and.api.ServicePool
import org.sopt.and.api.dto.RequestLoginDto


class SignInViewModel : ViewModel() {
    private val loginService = ServicePool.loginService

    private val _snackbarMessage = MutableStateFlow<String?>(null)
    val snackbarMessage: StateFlow<String?> get() = _snackbarMessage

    private var sharedPreferences: SharedPreferences? = null

    fun initializePreferences(context: Context) {
        sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    }

    private fun saveToken(token: String) {
        sharedPreferences?.edit()?.putString("token", token)?.apply()
    }

    fun loginUser(username: String, password: String) {
        viewModelScope.launch {
            try {
                val response = loginService.postLogin(RequestLoginDto(username, password))
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
