package com.alorma.groceries.business.ui.dashboard

import com.alorma.groceries.business.ui.UiState

data class DashboardUiState(
  val options: UiState<List<String>, Nothing> = UiState.Initial(),
  val pendingOrders: UiState<List<String>, () -> Unit> = UiState.Initial(),
  val readyOrders: UiState<List<String>, () -> Unit> = UiState.Initial(),
)