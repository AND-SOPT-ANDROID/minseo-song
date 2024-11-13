package org.sopt.and.ui.signup

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel


class SignUpViewModel: ViewModel() {
    val PASSWORD_MIN_LENGTH = 8
    val PASSWORD_MAX_LENGTH = 20
    val PASSWORD_REGEX = Regex("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{$PASSWORD_MIN_LENGTH,$PASSWORD_MAX_LENGTH}\$")

    var sharedPreferences: SharedPreferences? = null

    fun initializePreferences(context: Context){
        sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    }

    fun saveUserInfo(id: String, password: String){
        sharedPreferences?.edit()?.apply(){
            putString("userId", id)
            putString("userPassWord", password)
            apply()
        }
    }

    fun isAbleEmail(email: String): Boolean{
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isAblePassword(password: String): Boolean{
        return PASSWORD_REGEX.matches(password)
    }
}