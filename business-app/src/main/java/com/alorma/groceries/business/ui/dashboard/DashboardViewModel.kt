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
  private val _state: MutableStateFlow<UiState<DashboardUiState>> = MutableStateFlow(
    UiState.Initial(DashboardUiState())
  )
  val state: StateFlow<UiState<DashboardUiState>> = _state.asStateFlow()

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
              pendingCommands = UiState.Success(listOf("A", "B", "C"))
            )
          )
        } else {
          state
        }
      }
    }
  }
}