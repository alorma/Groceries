package com.alorma.groceries.business

import android.app.Application
import com.alorma.groceries.business.ui.dashboard.di.DashboardUiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class BusinessApplication : Application() {

  override fun onCreate() {
    super.onCreate()

    startKoin{
      androidLogger()
      androidContext(this@BusinessApplication)
      modules(
        DashboardUiModule()
      )
    }
  }
}