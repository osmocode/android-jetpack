package fr.uge.plutus.ui.ant

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember

@Composable
fun Ant(
    colors: AntColors = Ant.colors,
    spacing: AntSpacing = Ant.spacing,
    shapes: AntShape = Ant.shapes,
    content: @Composable () -> Unit
) {

    // Explicit creation disabled
    var rememberedColors = remember { colors.copy() }.apply { updateColorsFrom(colors) }

    CompositionLocalProvider(
        LocalColors provides rememberedColors,
        LocalSpacing provides spacing,
        LocalShaping provides shapes,
        content = content
    )
}

object Ant {

    val colors: AntColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val spacing: AntSpacing
        @Composable
        @ReadOnlyComposable
        get() = LocalSpacing.current

    val shapes: AntShape
        @Composable
        @ReadOnlyComposable
        get() = LocalShaping.current

}