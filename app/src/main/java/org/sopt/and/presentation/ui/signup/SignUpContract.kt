package org.sopt.and.presentation.ui.signup

import org.sopt.and.presentation.core.UiEvent
import org.sopt.and.presentation.core.UiSideEffect
import org.sopt.and.presentation.core.UiState

data class SignUpState(
    val userId: String = "",
    val userPassWord: String = "",
    val userHobby: String = "",
    val isLoading: Boolean = false
) : UiState

sealed class SignUpEvent : UiEvent {
    data class UserIdChanged(val userId: String) : SignUpEvent()
    data class UserPasswordChanged(val userPassword: String) : SignUpEvent()
    data class UserHobbyChanged(val userHobby: String) : SignUpEvent()
    object SignUpClicked : SignUpEvent()
}

sealed class SignUpSideEffect : UiSideEffect {
    data class ShowToast(val message: String) : SignUpSideEffect()
    object NavigateToSignIn : SignUpSideEffect()
}
