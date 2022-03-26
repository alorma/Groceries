package com.alorma.groceries.business.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alorma.groceries.business.ui.UiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DashboardViewModel : ViewModel() {
  private val _state: MutableStateFlow<UiState<DashboardUiState, Nothing>> = MutableStateFlow(
    UiState.Initial(DashboardUiState())
  )
  val state: StateFlow<UiState<DashboardUiState, Nothing>> = _state.asStateFlow()

  init {
    viewModelScope.launch {
      delay(1_000)
      val options = listOf("Schedule", "Stock", "Orders", "Clients", "Settings")
      _state.update {
        UiState.Success(
          DashboardUiState(
            options = UiState.Success(options)
          )
        )
      }
      delay(1_000)
      _state.update { state ->
        if (state is UiState.Success) {
          UiState.Success(
            state.data.copy(
              pendingOrders = UiState.Error(
                errorState = ::retryPendingCommands
              )
            )
          )
        } else {
          state
        }
      }
    }
  }

  private fun retryPendingCommands() {
    viewModelScope.launch {
      _state.update { state ->
        if (state is UiState.Success) {
          UiState.Success(
            state.data.copy(
              pendingOrders = UiState.Initial()
            )
          )
        } else {
          state
        }
      }
      delay(1_500)
      _state.update { state ->
        if (state is UiState.Success) {
          UiState.Success(
            state.data.copy(
              pendingOrders = UiState.Success(
                data = listOf("A", "B", "C")
              )
            )
          )
        } else {
          state
        }
      }
    }
  }
}