package com.alorma.groceries.business.ui

sealed class UiState<out T, out E> {
  data class Initial<T>(val data: T? = null) : UiState<T, Nothing>()
  data class Success<T>(val data: T) : UiState<T, Nothing>()
  data class Error<E>(val errorState: E) : UiState<Nothing, E>()
}
