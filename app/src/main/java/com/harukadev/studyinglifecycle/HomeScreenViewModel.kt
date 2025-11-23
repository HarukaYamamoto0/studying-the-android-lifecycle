package com.harukadev.studyinglifecycle

import android.util.Log
import androidx.lifecycle.ViewModel
import com.harukadev.studyinglifecycle.HomeScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeScreenViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(HomeScreenState())
    val uiState = _uiState.asStateFlow()

    fun setText(text: String) {
        _uiState.update { it.copy(text = text) }
    }

    init {
        Log.d("LifecycleStage", "HomeScreenViewModel - init: $this")
    }

    override fun onCleared() {
        Log.d("LifecycleStage", "HomeScreenViewModel - onCleared: $this")
    }
}