package com.ethereal.ibottaofferstest.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = NavButtonColor,
    secondary = Color.White,
    background = Color.Black,
    onBackground = ImageBackground
)

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = NavButtonColor,
    secondary = AmountColor,
    background = Color.White,
    onBackground = ImageBackground
)

@Composable
fun IbottaOffersTestTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}