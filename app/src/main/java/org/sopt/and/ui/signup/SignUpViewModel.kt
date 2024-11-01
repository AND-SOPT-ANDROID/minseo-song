package org.sopt.and.ui.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

const val PASSWORD_MIN_LENGTH = 8
const val PASSWORD_MAX_LENGTH = 20
val PASSWORD_REGEX = Regex("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{$PASSWORD_MIN_LENGTH,$PASSWORD_MAX_LENGTH}\$")


class SignUpViewModel: ViewModel() {
    var userId by mutableStateOf("")
    var userPassword by mutableStateOf("")

    fun updateUserId(id: String) {
        userId = id
    }

    fun updateUserPassword(password: String) {
        userPassword = password
    }

    fun isAbleEmail(email: String): Boolean{
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isAblePassword(password: String): Boolean{
        return PASSWORD_REGEX.matches(password)
    }
}