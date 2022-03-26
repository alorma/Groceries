package com.alorma.groceries.business.ui.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.alorma.groceries.business.ui.UiState
import com.alorma.groceries.uikit.Section
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
  dashboardViewModel: DashboardViewModel = getViewModel()
) {
  val state by dashboardViewModel.state.collectAsState()

  when (state) {
    is UiState.Initial -> DashboardLoading()
    is UiState.Success -> DashboardContent((state as UiState.Success<DashboardUiState>).data)
    is UiState.Error -> DashboardError()
  }
}

@Composable
fun DashboardLoading() {
  Box(
    modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.Center,
  ) {
    CircularProgressIndicator()
  }
}

@Composable
fun DashboardError() {
  Box(
    modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.Center,
  ) {
    Text(text = "Error")
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardContent(
  state: DashboardUiState
) {
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
      item(key = { "options" }) {
        DashboardOptions(state.options)
      }
      item(key = "pending orders") {
        OrdersList("Pending", state.pendingOrders)
      }
      item(key = "ready orders") {
        OrdersList("Ready", state.readyOrders)
      }
    }
  }
}

@Composable
fun DashboardOptions(
  options: UiState<List<String>, Nothing>
) {
  when (options) {
    is UiState.Initial -> DashboardLoading()
    is UiState.Success -> ManageOptions(options.data)
    is UiState.Error -> DashboardError()
  }
}

@Composable
fun OrdersList(
  title: String,
  pendingCommands: UiState<List<String>, () -> Unit>
) {
  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(horizontal = 8.dp),
    verticalArrangement = Arrangement.spacedBy(8.dp)
  ) {
    Text(
      modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp),
      text = title,
      style = MaterialTheme.typography.headlineMedium,
      color = MaterialTheme.colorScheme.primary,
    )

    when (pendingCommands) {
      is UiState.Initial -> DashboardLoading()
      is UiState.Success -> {
        Column(
          modifier = Modifier.fillMaxSize(),
          verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          pendingCommands.data.forEach { pendingCommand ->
            Row(
              modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            ) {
              Text(text = "$title command: $pendingCommand")
            }
          }
        }
      }
      is UiState.Error -> {
        Box(
          modifier = Modifier.fillMaxSize(),
          contentAlignment = Alignment.Center,
        ) {
          Button(
            onClick = { pendingCommands.errorState() }
          ) {
            Text(text = "Retry")
          }
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