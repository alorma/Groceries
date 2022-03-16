package com.alorma.groceries.business

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.ui.res.stringResource
import com.alorma.groceries.business.ui.theme.GroceriesTheme

class MainActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      GroceriesTheme {
        Scaffold(
          topBar = {
            TopAppBar(
              title = { Text(text = stringResource(id = R.string.app_name)) },
            )
          }
        ) {
        }
      }
    }
  }
}