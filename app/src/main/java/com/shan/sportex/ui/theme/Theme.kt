package com.shan.sportex.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = GreenAccent,
    primaryVariant = GreenAccent,
    secondary = GreenAccent,

    background = DarkBackground,
    surface = DarkCard,

    onPrimary = DarkBackground,
    onSecondary = DarkBackground,
    onBackground = LightText,
    onSurface = LightText
)

@Composable
fun SportExTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = DarkColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
