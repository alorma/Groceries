package com.alorma.groceries.business.ui.dashboard

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DashboardViewModel : ViewModel() {
  private val _optionsState: MutableStateFlow<List<String>> = MutableStateFlow(
    listOf("Schedule", "Stock", "Orders", "Clients", "Settings")
  )
  val optionsState: StateFlow<List<String>> = _optionsState.asStateFlow()
}