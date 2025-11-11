package com.example.cineplus.ui.theme


import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = TealPrimary,
    onPrimary = Color.White,
    secondary = GreenAccent,
    onSecondary = Color.White,
    background = BackgroundLight,
    surface = Color.White,
    onBackground = TextPrimary,
    onSurface = TextSecondary
)

private val DarkColors = darkColorScheme(
    primary = TealLight,
    onPrimary = Color.Black,
    secondary = GreenAccent,
    onSecondary = Color.Black,
    background = Color(0xFF1B1B1B),
    surface = Color(0xFF2C2C2C),
    onBackground = Color.White,
    onSurface = Color.White
)

@Composable
fun CinePlusTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colors,
        typography = AppTypography,
        content = content
    )
}
