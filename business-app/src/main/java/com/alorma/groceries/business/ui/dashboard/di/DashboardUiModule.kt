package com.alorma.groceries.business.ui.dashboard.di

import com.alorma.groceries.business.ui.dashboard.DashboardViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object DashboardUiModule {
  operator fun invoke() = module {
    viewModel { DashboardViewModel() }
  }
}