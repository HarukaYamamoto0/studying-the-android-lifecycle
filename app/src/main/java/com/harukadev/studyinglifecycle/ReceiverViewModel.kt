package com.harukadev.studyinglifecycle

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ReceiverViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ReceiverState())
    val uiState = _uiState.asStateFlow()

    fun update(
        sharedText: String?,
        id: String?
    ) {
        _uiState.update { it.copy(sharedText = sharedText, id = id) }
    }
}