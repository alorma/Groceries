package com.alorma.groceries.client

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.alorma.groceries.client.ui.theme.GroceriesTheme
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState

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
          Column(
            modifier = Modifier.padding(16.dp)
          ) {
            val valueRange = 100f..300f
            val valueRangeHalf = (valueRange.endInclusive + valueRange.start) / 2
            var mapHeight by remember { mutableStateOf(valueRangeHalf) }

            val updateAnimationHeight by animateFloatAsState(targetValue = mapHeight)

            Column(
              modifier = Modifier.padding(16.dp),
            ) {
              Slider(
                valueRange = valueRange,
                value = updateAnimationHeight,
                onValueChange = { mapHeight = it },
              )

              when (updateAnimationHeight) {
                valueRange.start -> {
                  Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { mapHeight = valueRange.endInclusive },
                  ) {
                    Text(text = "Expand")
                  }
                }
                valueRange.endInclusive -> {
                  Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { mapHeight = valueRange.start },
                  ) {
                    Text(text = "Collapse")
                  }
                }
                else -> {
                  Row {
                    Button(
                      modifier = Modifier.weight(1f),
                      onClick = { mapHeight = valueRange.endInclusive },
                    ) {
                      Text(text = "Expand")
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Button(
                      modifier = Modifier.weight(1f),
                      onClick = { mapHeight = valueRange.start },
                    ) {
                      Text(text = "Collapse")
                    }
                  }
                }
              }
            }

            Card(
              modifier = Modifier.wrapContentSize(),
              shape = RoundedCornerShape(16.dp)
            ) {
              val mapModifier = Modifier.height(updateAnimationHeight.dp)
              Map(mapModifier)
            }
          }
        }
      }
    }
  }

  @Composable
  private fun Map(modifier: Modifier = Modifier) {
    val singapore = LatLng(41.557465, 2.0139882)
    val cameraPositionState = rememberCameraPositionState {
      position = CameraPosition.fromLatLngZoom(singapore, 15f)
    }
    GoogleMap(
      modifier = modifier.fillMaxWidth(),
      uiSettings = MapUiSettings(compassEnabled = true),
      cameraPositionState = cameraPositionState
    )
  }
}
