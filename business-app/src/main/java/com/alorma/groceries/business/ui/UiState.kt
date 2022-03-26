package com.alorma.groceries.business.ui

sealed class UiState<T> {
  data class Initial<T>(val data: T? = null) : UiState<T>()
  data class Success<T>(val data: T) : UiState<T>()
  sealed class Error() : UiState<Nothing>()
}
