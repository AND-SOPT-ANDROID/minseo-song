package org.sopt.and.ui.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SignUpViewModel : ViewModel() {
    var userId by mutableStateOf("")
    var userPassword by mutableStateOf("")

    fun updateUserId(id: String) {
        userId = id
    }

    fun updateUserPassword(password: String) {
        userPassword = password
    }
}