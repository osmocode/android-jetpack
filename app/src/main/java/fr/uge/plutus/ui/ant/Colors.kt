package fr.uge.plutus.ui.ant

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color


@Stable
class AntColors(
    primary: Color,
    success: Color,
    warning: Color,
    danger: Color
) {
    var primary by mutableStateOf(primary, structuralEqualityPolicy())
        internal set

    var success by mutableStateOf(success, structuralEqualityPolicy())
        internal set

    var warning by mutableStateOf(warning, structuralEqualityPolicy())
        internal set

    var danger by mutableStateOf(danger, structuralEqualityPolicy())
        internal set

    fun copy(
        primary: Color = this.primary,
        success: Color = this.success,
        warning: Color = this.warning,
        danger: Color = this.danger
    ): AntColors = AntColors(
        primary,
        success,
        warning,
        danger
    )
}

fun defaultAntColors(
    primary: Color = Color(0xFF1677ff),
    success: Color = Color(0xFF00b578),
    warning: Color = Color(0xFFff8f1f),
    danger: Color = Color(0xFFff3141)
): AntColors = AntColors(
    primary,
    success,
    warning,
    danger
)

internal fun AntColors.updateColorsFrom(other: AntColors) {
    primary = other.primary
    success = other.success
    warning = other.warning
    danger = other.danger
}

internal val LocalColors = staticCompositionLocalOf { defaultAntColors() }