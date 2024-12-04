package org.sopt.and.ui.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.sopt.and.api.ServicePool
import org.sopt.and.api.dto.request.RequestUserDto


class SignUpViewModel : ViewModel() {
    private val userService = ServicePool.userService

    var userId = MutableStateFlow("")
        private set
    var userPassWord = MutableStateFlow("")
        private set
    var userHobby = MutableStateFlow("")
        private set

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage

    fun updateUserId(id: String) {
        userId.value = id
    }

    fun updateUserPassword(password: String) {
        userPassWord.value = password
    }

    fun updateUserHobby(hobby: String) {
        userHobby.value = hobby
    }

    fun signUpUser(onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                val response = userService.postUser(
                    RequestUserDto(
                        username = userId.value,
                        password = userPassWord.value,
                        hobby = userHobby.value
                    )
                )
                onSuccess()
            } catch (e: Exception) {
                _errorMessage.value = "오류 발생: ${e.message}"
            }
        }
    }
}
