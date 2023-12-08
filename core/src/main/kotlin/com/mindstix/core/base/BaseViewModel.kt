package com.mindstix.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

/**
 * This is the interface for the view state.
 */
interface ViewState

/**
 * This is the interface for the user intent.
 */
interface UserIntent

/**
 * This is the interface for the navigation effect.
 */
interface NavEffect

/**
 * This is the Base view model.
 */
abstract class BaseViewModel<Intent : UserIntent, State : ViewState, Effect : NavEffect> :
    ViewModel() {

    // Create the initial state of the view.
    private val initialState: State by lazy { createInitialState() }
    abstract fun createInitialState(): State

    // Get the current state.
    val currentState: State
        get() = viewState.value

    private val _viewState: MutableStateFlow<State> = MutableStateFlow(initialState)
    val viewState
        get() = _viewState.asStateFlow()

    private val _intent: MutableSharedFlow<Intent> = MutableSharedFlow()
    val intent
        get() = _intent.asSharedFlow()

    private val _effect: Channel<Effect> = Channel()
    val effect
        get() = _effect.receiveAsFlow()

    init {
        subscribeIntent()
    }

    /**
     * Start listening to the intent.
     */
    private fun subscribeIntent() {
        viewModelScope.launch {
            intent.collect {
                handleIntent(it)
            }
        }
    }

    /**
     * Handle each intent.
     */
    abstract fun handleIntent(intent: Intent)

    /**
     * Set newUserIntent.
     */
    fun performAction(intent: Intent) {
        val newUserIntent = intent
        viewModelScope.launch { _intent.emit(newUserIntent) }
    }

    /**
     * Emit the new ViewState.
     */
    protected fun emitViewState(reduce: State.() -> State) {
        val newState = currentState.reduce()
        _viewState.value = newState
    }

    /**
     * Set the new NavEffect.
     */
    protected fun sendNavEffect(builder: () -> Effect) {
        val effectValue = builder()
        viewModelScope.launch { _effect.send(effectValue) }
    }
}
