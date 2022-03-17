package com.alorma.groceries.business

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alorma.groceries.business.ui.theme.BusinessTheme
import com.alorma.groceries.uikit.Section

class MainActivity : ComponentActivity() {

  @OptIn(ExperimentalMaterial3Api::class)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      val items = remember { mutableListOf("Schedule", "Stock", "Orders", "Clients", "Settings") }
      BusinessTheme {
        Scaffold {
          Column {
            ManageOptions(items)
          }
        }
      }
    }
  }

  @OptIn(ExperimentalFoundationApi::class)
  @Composable
  private fun ManageOptions(items: List<String>) {
    LazyVerticalGrid(
      modifier = Modifier.fillMaxWidth(),
      verticalArrangement = Arrangement.spacedBy(8.dp),
      horizontalArrangement = Arrangement.spacedBy(8.dp),
      contentPadding = PaddingValues(16.dp),
      columns = GridCells.Fixed(2),
    ) {
      items(items) { item ->
        key(item) {
          Section(
            modifier = Modifier
              .fillMaxWidth()
              .heightIn(72.dp),
            title = item,
            onClick = { },
          )
        }
      }
    }
  }
}
