package com.harukadev.studyinglifecycle.homescreen

sealed interface HomeScreenEvent {
    data class Error(val message: String) : HomeScreenEvent
    data object NavigateToReceiver : HomeScreenEvent
}
