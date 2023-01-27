package fr.uge.plutus.ui.ant

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable


private val DarkAntColors = defaultAntColors()

private val LightAntColors = defaultAntColors()

@Composable
fun AntTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    Ant(
        colors = if (darkTheme) DarkAntColors else LightAntColors,
        content = content
    )
}