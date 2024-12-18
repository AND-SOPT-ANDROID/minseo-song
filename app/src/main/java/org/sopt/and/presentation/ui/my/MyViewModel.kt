package org.sopt.and.presentation.ui.my

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.sopt.and.domain.usecase.HobbyUseCase
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val hobbyUseCase: HobbyUseCase
): ViewModel() {
    private val _hobby = MutableStateFlow<String>("")
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
            val result = hobbyUseCase(token)
            result.onSuccess { response ->
                _hobby.value = response.result.hobby
            }.onFailure { error ->
                _errorMessage.value = when (error) {
                    is retrofit2.HttpException -> "서버 오류: ${error.code()} ${error.message()}"
                    is java.net.UnknownHostException -> "네트워크 연결 오류"
                    else -> "${error.message}"
                }
            }
        }
    }
}
