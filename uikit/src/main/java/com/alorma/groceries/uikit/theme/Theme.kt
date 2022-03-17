package com.alorma.groceries.uikit.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun BaseTheme(
  useDarkTheme: Boolean = isSystemInDarkTheme(),
  lightColors: ColorScheme,
  darkColors: ColorScheme,
  content: @Composable () -> Unit
) {
  val colors = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
    val context = LocalContext.current
    if (!useDarkTheme) {
      dynamicLightColorScheme(context)
    } else {
      dynamicDarkColorScheme(context)
    }
  } else {
    if (!useDarkTheme) {
      lightColors
    } else {
      darkColors
    }
  }

  MaterialTheme(
    colorScheme = colors,
    typography = AppTypography,
    content = content
  )
}