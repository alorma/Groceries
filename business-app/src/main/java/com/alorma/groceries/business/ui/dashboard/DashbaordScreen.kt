package com.alorma.groceries.business.ui.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.alorma.groceries.uikit.Section
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
  dashboardViewModel: DashboardViewModel = getViewModel()
) {
  val options by dashboardViewModel.optionsState.collectAsState()

  val scrollBehavior = remember { TopAppBarDefaults.pinnedScrollBehavior() }

  Scaffold(
    modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
    topBar = {
      CenterAlignedTopAppBar(
        title = { Text(text = "Fruteria Pili") },
        scrollBehavior = scrollBehavior,
      )
    }
  ) { innerPadding ->
    LazyColumn(
      contentPadding = innerPadding,
      verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      item(key = { "manage options" }) {
        ManageOptions(options)
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