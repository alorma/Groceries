package com.alorma.groceries.business.ui.uikit

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Section(
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
  backgroundColor: Color = MaterialTheme.colors.secondary,
  title: String,
) {
  Card(
    onClick = onClick,
    modifier = modifier,
    shape = RoundedCornerShape(24.dp),
    backgroundColor = backgroundColor.copy(alpha = 0.12f).compositeOver(MaterialTheme.colors.surface),
    indication = rememberRipple(color = backgroundColor),
  ) {
    Box(modifier = Modifier.padding(8.dp)) {
      Text(text = title)
    }
  }
}

@Preview
@Composable
fun SectionPreview() {
  MaterialTheme {
    Section(
      onClick = {}, modifier = Modifier
        .height(120.dp)
        .width(200.dp),
      title = "Horarios"
    )
  }
}