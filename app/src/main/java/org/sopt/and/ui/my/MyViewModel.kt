package org.sopt.and.ui.my

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import okhttp3.ResponseBody
import org.sopt.and.api.ServicePool
import org.sopt.and.api.dto.ResponseErrorDto
import org.sopt.and.api.dto.ResponseHobbySuccessDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyViewModel() : ViewModel() {
    private val hobbyService = ServicePool.hobbyService

    private val _hobby = MutableStateFlow<String>("")
    val hobby: StateFlow<String> get() = _hobby

    fun getUserHobby(sharedPreferences: SharedPreferences) {
        val token = sharedPreferences.getString("token", null)
        if (token.isNullOrEmpty()) {
            Log.e("MyViewModel", "토큰 없음")
            return
        }

        hobbyService.getMyHobby(token).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    val successBody = response.body()?.string()
                    val successDto =
                        Json.decodeFromString<ResponseHobbySuccessDto>(successBody ?: "")
                    viewModelScope.launch {
                        _hobby.emit(successDto.result.hobby)
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    val errorDto = errorBody?.let { Json.decodeFromString<ResponseErrorDto>(it) }
                    val errorMessage = when (response.code()) {
                        401 -> "토큰이 없습니다."
                        403 -> "유효하지 않은 토큰입니다."
                        404 -> "잘못된 경로로 요청했습니다."
                        else -> "알 수 없는 오류가 발생했습니다."
                    }
                    Log.e("MyViewModel", errorMessage)
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.e("MyViewModel", "Network error: ${t.message}")
            }
        })
    }
}