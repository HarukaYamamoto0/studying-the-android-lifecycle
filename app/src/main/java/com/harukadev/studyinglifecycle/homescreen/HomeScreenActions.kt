package com.harukadev.studyinglifecycle.homescreen

sealed interface HomeScreenActions {
    data class OnSetText(val text: String) : HomeScreenActions
    data object Send : HomeScreenActions
}