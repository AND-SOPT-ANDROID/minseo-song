package org.sopt.and.ui.signup

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.serialization.json.Json
import okhttp3.ResponseBody
import org.sopt.and.api.ServicePool
import org.sopt.and.api.dto.RequestUserDto
import org.sopt.and.api.dto.ResponseErrorDto
import org.sopt.and.api.dto.ResponseUserSuccessDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SignUpViewModel : ViewModel() {
    private val userService by lazy { ServicePool.userService }

    fun signUpUser(
        username: String,
        password: String,
        hobby: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        val requestUserDto = RequestUserDto(username = username, password = password, hobby = hobby)

        userService.postUser(requestUserDto).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {
                if (response.isSuccessful) {
                    val successBody = response.body()?.string()
                    val successDto =
                        Json.decodeFromString<ResponseUserSuccessDto>(successBody ?: "")
                    onSuccess()
                } else {
                    val errorBody = response.errorBody()?.string()
                    val errorDto = errorBody?.let { Json.decodeFromString<ResponseErrorDto>(it) }
                    val errorMessage = when (response.code()) {
                        400 -> when (errorDto?.code) {
                            "01" -> "닉네임, 비밀번호, 취미가 8자를 넘기면 안됩니다."
                            else -> "잘못된 요청입니다."
                        }

                        409 -> when (errorDto?.code) {
                            "00" -> "닉네임이 중복됩니다."
                            else -> "충돌이 발생했습니다."
                        }

                        else -> "알 수 없는 오류가 발생했습니다."
                    }
                    onFailure(errorMessage)
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.e("SignUpViewModel", "Failure: ${t.message}")
            }
        })
    }
}