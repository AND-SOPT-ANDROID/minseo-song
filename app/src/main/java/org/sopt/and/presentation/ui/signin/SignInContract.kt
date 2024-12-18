package org.sopt.and.presentation.ui.signin

import org.sopt.and.presentation.core.UiEvent
import org.sopt.and.presentation.core.UiSideEffect
import org.sopt.and.presentation.core.UiState

data class SignInState(
    val userId: String = "",
    val userPassWord: String = "",
    val isLoading: Boolean = false
) : UiState

sealed class SignInEvent : UiEvent {
    data class UserIdChanged(val userId: String) : SignInEvent()
    data class UserPasswordChanged(val password: String) : SignInEvent()
    object SignInClicked : SignInEvent()
}

sealed class SignInSideEffect : UiSideEffect {
    data class ShowSnackBar(val message: String) : SignInSideEffect()
    object NavigateToMyScreen : SignInSideEffect()
}
