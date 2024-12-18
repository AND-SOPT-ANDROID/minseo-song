package org.sopt.and.presentation.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class BaseViewModel<State : UiState, SideEffect : UiSideEffect, Event : UiEvent> : ViewModel() {

    abstract fun createInitialState(): State

    private val _uiState = MutableStateFlow(createInitialState())
    val uiState: StateFlow<State> = _uiState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<SideEffect>()
    val sideEffect: SharedFlow<SideEffect> = _sideEffect.asSharedFlow()

    fun setEvent(event: Event) {
        viewModelScope.launch { handleEvent(event) }
    }

    protected fun setState(reducer: State.() -> State) {
        _uiState.value = _uiState.value.reducer()
    }

    protected fun setSideEffect(builder: () -> SideEffect) {
        viewModelScope.launch { _sideEffect.emit(builder()) }
    }

    protected abstract suspend fun handleEvent(event: Event)
}
