package org.sopt.and.presentation.ui.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.sopt.and.data.dataremote.model.request.RequestUserDto
import org.sopt.and.domain.usecase.SignUpUseCase
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {
    var userId = MutableStateFlow("")
        private set
    var userPassWord = MutableStateFlow("")
        private set
    var userHobby = MutableStateFlow("")
        private set

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage

    private val _successMessage = MutableStateFlow<String?>(null)
    val successMessage: StateFlow<String?> get() = _successMessage

    fun updateUserId(id: String) {
        userId.value = id
    }

    fun updateUserPassword(password: String) {
        userPassWord.value = password
    }

    fun updateUserHobby(hobby: String) {
        userHobby.value = hobby
    }

    fun signUpUser() {
        viewModelScope.launch {
            val result = signUpUseCase.invoke(userId.value, userPassWord.value, userHobby.value)
            result.onSuccess { response ->
                _successMessage.value = "회원가입 성공! 유저 ID: ${response.result.userNumber}"
                clearErrorMessage()
            }.onFailure { error ->
                _errorMessage.value = when (error) {
                    is retrofit2.HttpException -> "서버 오류: ${error.code()} ${error.message()}"
                    is java.net.UnknownHostException -> "네트워크 연결 오류"
                    else -> "알 수 없는 오류: ${error.message}"
                }
                clearSuccessMessage()
            }
        }
    }

    private fun clearErrorMessage() {
        _errorMessage.value = null
    }

    private fun clearSuccessMessage() {
        _successMessage.value = null
    }
}
