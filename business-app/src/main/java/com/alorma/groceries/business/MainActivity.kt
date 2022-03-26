package com.alorma.groceries.business

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import com.alorma.groceries.business.ui.dashboard.DashboardScreen
import com.alorma.groceries.business.ui.theme.BusinessTheme

class MainActivity : ComponentActivity() {

  @OptIn(ExperimentalMaterial3Api::class)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      BusinessTheme {
        DashboardScreen()
      }
    }
  }
}
