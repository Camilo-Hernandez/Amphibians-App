package com.camihruiz24.amphibians_app.ui.screens

sealed interface UiState<out T> {
    data class Success<out T>(val info: T) : UiState<T>
    data class Error(val error: Throwable) : UiState<Nothing>
    data object Loading : UiState<Nothing>
}
