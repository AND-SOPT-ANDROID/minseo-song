package org.sopt.and.ui.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import org.sopt.and.model.UserInfo

const val PASSWORD_MIN_LENGTH = 8
const val PASSWORD_MAX_LENGTH = 20
val PASSWORD_REGEX = Regex("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{$PASSWORD_MIN_LENGTH,$PASSWORD_MAX_LENGTH}\$")


class SignUpViewModel: ViewModel() {
    var userInfo by mutableStateOf(UserInfo("",""))

    fun updateUserInfo(id: String, password: String){
        userInfo = userInfo.copy(userId = id, userPassWord = password)
    }

    fun isAbleEmail(email: String): Boolean{
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isAblePassword(password: String): Boolean{
        return PASSWORD_REGEX.matches(password)
    }
}