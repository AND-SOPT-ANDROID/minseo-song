package org.sopt.and.ui.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.sopt.and.api.ServicePool
import org.sopt.and.api.dto.RequestUserDto


class SignUpViewModel : ViewModel() {
    private val userService = ServicePool.userService

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage

    fun signUpUser(username: String, password: String, hobby: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                val response = userService.postUser(RequestUserDto(username, password, hobby))
                onSuccess()
            } catch (e: Exception) {
                _errorMessage.value = "오류 발생: ${e.message}"
            }
        }
    }
}
