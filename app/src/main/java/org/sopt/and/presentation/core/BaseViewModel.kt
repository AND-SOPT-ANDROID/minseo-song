package org.sopt.and.presentation.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class BaseViewModel<State : UiState, SideEffect : UiSideEffect, Event : UiEvent> : ViewModel() {

    // 초기 State를 생성하기 위한 추상 함수
    abstract fun createInitialState(): State

    // 현재 상태를 저장하는 StateFlow
    private val _uiState = MutableStateFlow(createInitialState())
    val uiState: StateFlow<State> = _uiState.asStateFlow()

    // SideEffect를 전달하는 SharedFlow
    private val _sideEffect = MutableSharedFlow<SideEffect>()
    val sideEffect: SharedFlow<SideEffect> = _sideEffect.asSharedFlow()

    // Event를 처리하는 함수
    fun setEvent(event: Event) {
        viewModelScope.launch { handleEvent(event) }
    }

    // 상태를 변경하는 함수
    protected fun setState(reducer: State.() -> State) {
        _uiState.value = _uiState.value.reducer()
    }

    // SideEffect를 설정하는 함수
    protected fun setSideEffect(builder: () -> SideEffect) {
        viewModelScope.launch { _sideEffect.emit(builder()) }
    }

    // Event를 처리하기 위한 추상 함수
    protected abstract suspend fun handleEvent(event: Event)
}
