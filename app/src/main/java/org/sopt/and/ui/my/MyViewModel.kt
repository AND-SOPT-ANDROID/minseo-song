package org.sopt.and.ui.my

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.sopt.and.api.ServicePool

class MyViewModel : ViewModel() {
    private val userService = ServicePool.userService

    val _hobby = MutableStateFlow<String>("")
    val hobby: StateFlow<String> get() = _hobby

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage

    fun getUserHobby(sharedPreferences: SharedPreferences) {
        val token = sharedPreferences.getString("token", null)
        if (token.isNullOrEmpty()) {
            _errorMessage.value = "토큰 없음"
            return
        }

        viewModelScope.launch {
            try {
                val response = userService.getMyHobby(token)
                _hobby.value = response.result.hobby
            } catch (e: Exception) {
                _errorMessage.value = "오류 발생: ${e.message}"
            }
        }
    }
}
