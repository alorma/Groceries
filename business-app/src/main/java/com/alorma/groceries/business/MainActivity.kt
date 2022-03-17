package com.alorma.groceries.business

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
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
          Column(

          ) {
            ManageOptions(items)
          }
        }
      }
    }
  }

  @Composable
  private fun ManageOptions(items: List<String>) {
    Column(
      modifier = Modifier.padding(8.dp),
      verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      val chunks = items.chunked(2)
      chunks.forEach { chunkedItems ->
        Row(
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          chunkedItems.forEach { item ->
            key(item) {
              Section(
                modifier = Modifier
                  .fillMaxWidth()
                  .weight(1f)
                  .heightIn(72.dp),
                title = item,
                onClick = { },
              )
            }
          }
        }
      }

    }
  }
}
