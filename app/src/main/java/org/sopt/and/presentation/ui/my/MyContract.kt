package org.sopt.and.presentation.ui.my

import org.sopt.and.presentation.core.UiEvent
import org.sopt.and.presentation.core.UiState

data class MyState(
    val hobby: String = "",
    val isLoading: Boolean = false
) : UiState

sealed class MyEvent : UiEvent {
    data class LoadUserHobby(val token: String) : MyEvent()
}
