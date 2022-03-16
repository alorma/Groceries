package com.alorma.groceries.business

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alorma.groceries.business.ui.theme.GroceriesTheme
import com.alorma.groceries.business.ui.uikit.Section

class MainActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      val title by remember { mutableStateOf("Frutas Pili")}
      val items = remember { mutableListOf("Schedule", "Stock", "Orders", "Clients", "Settings") }
      GroceriesTheme {
        Scaffold {
          Column {
            Title(title)
            ManageOptions(items)
          }
        }
      }
    }
  }

  @Composable
  private fun ColumnScope.Title(title: String) {
    Text(
      modifier = Modifier.Companion
        .align(Alignment.CenterHorizontally)
        .padding(top = 16.dp, bottom = 8.dp),
      text = title,
      style = MaterialTheme.typography.h5,
    )
  }

  @OptIn(ExperimentalFoundationApi::class)
  @Composable
  private fun ManageOptions(items: List<String>) {
    LazyVerticalGrid(
      modifier = Modifier.fillMaxWidth(),
      verticalArrangement = Arrangement.spacedBy(8.dp),
      horizontalArrangement = Arrangement.spacedBy(8.dp),
      contentPadding = PaddingValues(16.dp),
      cells = GridCells.Fixed(2),
    ) {
      items(items) { item ->
        key(item) {
          Section(
            modifier = Modifier
              .fillMaxWidth()
              .heightIn(72.dp),
            backgroundColor = MaterialTheme.colors.onSurface,
            title = item,
            onClick = { },
          )
        }
      }
    }
  }
}
