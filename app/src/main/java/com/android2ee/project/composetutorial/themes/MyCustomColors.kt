package com.android2ee.project.composetutorial.themes

import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.Color

//
/** Created by Mathias Seguy also known as Android2ee on 18/03/2024.
 * The goal of this class is to have several set of Theme and easily switch between them
 *
 */

object MyCustomColors {
    /* Light Pattern  */
    /* -------------  */
    val lightColorScheme = ColorScheme(
        primary = Color(0xFF6200EE),
        onPrimary = Color.White,
        primaryContainer = Color(0xFF6200EE),
        onPrimaryContainer = Color.White,
        inversePrimary = Color.Black,
        secondary = Color(0xFF03DAC6),
        onSecondary = Color.Black,
        secondaryContainer = Color(0xFF03DAC6),
        onSecondaryContainer = Color.Black,
        tertiary = Color.Gray,
        onTertiary = Color.Black,
        tertiaryContainer = Color.Gray,
        onTertiaryContainer = Color.Black,
        background = Color.White,
        onBackground = Color.Black,
        surface = Color(0xFFF5F5F5),
        onSurface = Color.Black,
        surfaceVariant = Color(0xFFE0E0E0),
        onSurfaceVariant = Color.Black,
        surfaceTint = Color(0xFFBDBDBD),
        inverseSurface = Color.Black,
        inverseOnSurface = Color.White,
        error = Color(0xFFB00020),
        onError = Color.White,
        errorContainer = Color(0xFFB00020),
        onErrorContainer = Color.White,
        outline = Color.Black,
        outlineVariant = Color(0xFFE0E0E0),
        scrim = Color(0x99000000)
    )
    val lightSpecificColors = SpecificColors(
        sunlightOverTheTop = Color.Blue,
        sunlightTop = Color.Cyan,
        sunlightMiddle = Color.Yellow,
        sunlightBottom = Color.Red,
        skyColor = MaterialColors.Blue300,
        earthcolor = MaterialColors.Yellow900
    )

    /* Dark Pattern  */
    /* -------------  */
    val darkColorScheme = ColorScheme(
        primary = Color(0xFFBB86FC),
        onPrimary = Color.Black,
        primaryContainer = Color(0xFFBB86FC),
        onPrimaryContainer = Color.Black,
        inversePrimary = Color.White,
        secondary = Color(0xFF03DAC6),
        onSecondary = Color.Black,
        secondaryContainer = Color(0xFF03DAC6),
        onSecondaryContainer = Color.Black,
        tertiary = Color.Gray,
        onTertiary = Color.White,
        tertiaryContainer = Color.Gray,
        onTertiaryContainer = Color.White,
        background = Color.Black,
        onBackground = Color.White,
        surface = Color(0xFF121212),
        onSurface = Color.White,
        surfaceVariant = Color(0xFF1E1E1E),
        onSurfaceVariant = Color.White,
        surfaceTint = Color(0xFF333333),
        inverseSurface = Color.White,
        inverseOnSurface = Color.Black,
        error = Color(0xFFCF6679),
        onError = Color.Black,
        errorContainer = Color(0xFFCF6679),
        onErrorContainer = Color.Black,
        outline = Color.White,
        outlineVariant = Color(0xFF1E1E1E),
        scrim = Color(0x99000000)
    )

    val darkSpecificColors = SpecificColors(
        sunlightOverTheTop = MaterialColors.Amber200,
        sunlightTop = MaterialColors.Red400,
        sunlightMiddle = MaterialColors.Blue500,
        sunlightBottom = MaterialColors.PurpleA100,

        skyColor = MaterialColors.Indigo400,
        earthcolor = MaterialColors.Green800
    )
}

data class SpecificColors(
    val sunlightOverTheTop: Color,
    val sunlightTop: Color,
    val sunlightMiddle: Color,
    val sunlightBottom: Color,
    val skyColor: Color,
    val earthcolor: Color
)