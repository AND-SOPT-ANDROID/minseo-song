package org.sopt.and.ui.signin

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.serialization.json.Json
import okhttp3.ResponseBody
import org.sopt.and.api.ServicePool
import org.sopt.and.api.dto.RequestLoginDto
import org.sopt.and.api.dto.ResponseErrorDto
import org.sopt.and.api.dto.ResponseLoginSuccessDto
import org.sopt.and.model.UserInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SignInViewModel : ViewModel() {
    val loginService by lazy { ServicePool.loginService }
    var userInfo by mutableStateOf(UserInfo("", ""))
//    var sharedPreferences: SharedPreferences? = null

    val _snackbarMessage = MutableStateFlow<String?>(null)
    val snackbarMessage: StateFlow<String?> get() = _snackbarMessage

//    fun initializePreferences(context: Context) {
//        sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
//    }
//
//    fun updateUserInfo(id: String, password: String) {
//        userInfo = UserInfo(userId = id, userPassWord = password)
//    }

    fun loginUser(
        username: String,
        password: String,
        onSuccess: (String) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val requestLoginDto = RequestLoginDto(username = username, password = password)

        loginService.postLogin(requestLoginDto).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {
                if (response.isSuccessful) {
                    val successBody = response.body()?.string()
                    val successDto =
                        Json.decodeFromString<ResponseLoginSuccessDto>(successBody ?: "")
                    onSuccess("로그인 성공!")
                } else {
                    val errorBody = response.errorBody()?.string()
                    val errorDto = errorBody?.let { Json.decodeFromString<ResponseErrorDto>(it) }
                    val errorMessage = when (response.code()) {
                        400 -> when (errorDto?.code) {
                            "02" -> "로그인 정보가 올바르지 않습니다."
                            else -> "잘못된 요청입니다."
                        }

                        403 -> "비밀번호가 틀렸습니다."
                        else -> "알 수 없는 오류가 발생했습니다."
                    }
                    onFailure(errorMessage)
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                onFailure("네트워크 오류: ${t.message}")
                Log.e("SignInViewModel", "Failure: ${t.message}")
            }
        })
    }

//    fun performLogin() {
//        val savedUserId = sharedPreferences?.getString("userId", "") ?: ""
//        val savedUserPassword = sharedPreferences?.getString("userPassWord", "") ?: ""
//        val loginSuccess =
//            (userInfo.userId == savedUserId && userInfo.userPassWord == savedUserPassword)
//
//        _snackbarMessage.value = if (loginSuccess) {
//            "로그인 성공"
//        } else {
//            "로그인 실패"
//        }
//    }

    fun clearSnackbarMessage() {
        _snackbarMessage.value = null
    }
}