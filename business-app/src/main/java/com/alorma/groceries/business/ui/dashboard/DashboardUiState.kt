package com.alorma.groceries.business.ui.dashboard

import com.alorma.groceries.business.ui.UiState

data class DashboardUiState(
  val options: UiState<List<String>> = UiState.Initial(),
  val pendingCommands: UiState<List<String>> = UiState.Initial()
)