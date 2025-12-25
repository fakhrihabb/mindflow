package com.fawapps.mindflow.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = DeepBrown,
    onPrimary = TextOnBrown,
    primaryContainer = LightBrown,
    onPrimaryContainer = TextDarkBrown,
    secondary = MediumBrown,
    onSecondary = TextOnBrown,
    secondaryContainer = Tan,
    onSecondaryContainer = TextDarkBrown,
    tertiary = Tan,
    onTertiary = TextDarkBrown,
    background = LightCream,
    onBackground = TextDarkBrown,
    surface = Cream,
    onSurface = TextDarkBrown,
    surfaceVariant = LightTan,
    onSurfaceVariant = TextSecondary,
    error = WarmRedBrown,
    onError = TextOnBrown
)

// TODO: Define DarkColorScheme properly for Phase 2
private val DarkColorScheme = darkColorScheme(
    primary = LightBrown,
    onPrimary = TextDarkBrown,
    background = TextDarkBrown, // Dark background
    onBackground = LightCream
)

@Composable
fun MindFlowTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
