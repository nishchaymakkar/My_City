package com.example.mycity.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = dark_primary,
    onPrimary = dark_onPrimary,
    primaryContainer = dark_primaryContainer,
    onPrimaryContainer = dark_onPrimaryContainer,
    secondary = dark_secondary,
    onSecondary = dark_onSecondary,
    secondaryContainer = dark_secondaryContainer,
    onSecondaryContainer = dark_onSecondaryContainer,
    tertiary = dark_tertiary,
    onTertiary = dark_onTertiary,
    tertiaryContainer = dark_tertiaryContainer,
    onTertiaryContainer = dark_onTertiaryContainer,
    error = dark_error,
    onError = dark_onError,
    errorContainer = dark_errorContainer,
    onErrorContainer = dark_onErrorContainer,
    surfaceDim = dark_surfaceDim,
    surface = dark_Surface,
    surfaceBright = dark_SurfaceBright,
    surfaceContainerLowest = dark_SurfaceContainer_lowest,
    surfaceContainerLow = dark_SurfaceContainer_low,
    surfaceContainer = dark_SurfaceContainer,
    surfaceContainerHigh = dark_SurfaceContainer_high,
    surfaceContainerHighest = dark_SurfaceContainer_highest,
    onSurface = dark_onSurface,
    onSurfaceVariant = dark_onSurfaceVariant,
    outline = dark_Outline,
    outlineVariant = dark_OutlineVariant,
    inverseSurface = dark_inverseSurface,
    inverseOnSurface = dark_inverseOnSurface,
    inversePrimary = dark_inversePrimary,
    scrim = dark_Scrim,

)

private val LightColorScheme = lightColorScheme(
    primary = light_primary,
    onPrimary = light_onPrimary,
    primaryContainer = light_primaryContainer,
    onPrimaryContainer = light_onPrimaryContainer,
    secondary = light_secondary,
    onSecondary = light_onSecondary,
    secondaryContainer = light_secondaryContainer,
    onSecondaryContainer = light_onSecondaryContainer,
    tertiary = light_tertiary,
    onTertiary = light_onTertiary,
    tertiaryContainer = light_tertiaryContainer,
    onTertiaryContainer = light_onTertiaryContainer,
    error = light_error,
    onError = light_onError,
    errorContainer = light_errorContainer,
    onErrorContainer = light_onErrorContainer,
    surfaceDim = light_surfaceDim,
    surface = light_Surface,
    surfaceBright = light_SurfaceBright,
    surfaceContainerLowest = light_SurfaceContainer_lowest,
    surfaceContainerLow = light_SurfaceContainer_low,
    surfaceContainer = light_SurfaceContainer,
    surfaceContainerHigh = light_SurfaceContainer_high,
    surfaceContainerHighest = light_SurfaceContainer_highest,
    onSurface = light_onSurface,
    onSurfaceVariant = light_onSurfaceVariant,
    outline = light_Outline,
    outlineVariant = light_OutlineVariant,
    inverseSurface = light_inverseSurface,
    inverseOnSurface = light_inverseOnSurface,
    inversePrimary = light_inversePrimary,
    scrim = light_Scrim,


)

@Composable
fun MyCityTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}