package org.sopt.and.presentation.ui.my

import android.content.SharedPreferences
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.and.domain.usecase.HobbyUseCase
import org.sopt.and.presentation.core.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val hobbyUseCase: HobbyUseCase
) : BaseViewModel<MyState, Nothing, MyEvent>() {

    override fun createInitialState(): MyState = MyState()

    fun getUserHobby(sharedPreferences: SharedPreferences) {
        val token = sharedPreferences.getString("token", null)
        if (!token.isNullOrEmpty()) {
            setEvent(MyEvent.LoadUserHobby(token))
            loadUserHobby(token)
        }
    }

    override suspend fun handleEvent(event: MyEvent) {
        when (event) {
            is MyEvent.LoadUserHobby -> {}
        }
    }

    private fun loadUserHobby(token: String) {
        setState { copy(isLoading = true) }
        viewModelScope.launch {
            val result = hobbyUseCase(token)
            result.onSuccess { response ->
                setState { copy(hobby = response.result.hobby, isLoading = false) }
            }.onFailure {
                setState { copy(isLoading = false) }
            }
        }
    }
}
