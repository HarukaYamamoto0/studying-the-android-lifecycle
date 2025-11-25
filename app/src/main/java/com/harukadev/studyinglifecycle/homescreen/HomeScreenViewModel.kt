package com.harukadev.studyinglifecycle.homescreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(HomeScreenState())
    val uiState = _uiState.asStateFlow()

    private val _events = Channel<HomeScreenEvent>()
    val events = _events.receiveAsFlow()

    fun onAction(event: HomeScreenActions) {
        when (event) {
            HomeScreenActions.Send -> {
                if (_uiState.value.text.isNotEmpty() || _uiState.value.text.isNotBlank()) {
                    viewModelScope.launch { _events.send(HomeScreenEvent.NavigateToReceiver) }
                } else {
                    viewModelScope.launch {
                        _events.send(HomeScreenEvent.Error(message = "The text can't be empty, you fool!"))
                    }
                }
            }

            is HomeScreenActions.OnSetText -> {
                _uiState.update { it.copy(text = event.text) }
            }
        }
    }

    init {
        Log.d("LifecycleStage", "HomeScreenViewModel - init: $this")
    }

    override fun onCleared() {
        Log.d("LifecycleStage", "HomeScreenViewModel - onCleared: $this")
    }
}