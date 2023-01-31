package fr.uge.plutus.ui.ant

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color


@Stable
class AntColors(
    nav_background: Color,
    nav_item: Color,
    nav_item_focus: Color,
    nav_shadow: Color
) {
    var nav_background by mutableStateOf(nav_background, structuralEqualityPolicy())
        internal set

    var nav_item by mutableStateOf(nav_item, structuralEqualityPolicy())
        internal set

    var nav_item_focus by mutableStateOf(nav_item_focus, structuralEqualityPolicy())
        internal set

    var nav_shadow by mutableStateOf(nav_shadow, structuralEqualityPolicy())
        internal set

    fun copy(
        nav_background: Color = this.nav_background,
        nav_item_color: Color = this.nav_item,
        nav_item_focus: Color = this.nav_item_focus,
        nav_shadow: Color = this.nav_shadow
    ): AntColors = AntColors(
        nav_background,
        nav_item_color,
        nav_item_focus,
        nav_shadow
    )
}

fun defaultAntColors(
    nav_background: Color = Color(0xFFECF0F1),
    nav_item: Color = Color(0xFF95A5A6),
    nav_item_focus: Color = Color(0xFF2C3E50),
    nav_shadow: Color = Color(0xFF000000)
): AntColors = AntColors(
    nav_background,
    nav_item,
    nav_item_focus,
    nav_shadow
)

internal fun AntColors.updateColorsFrom(other: AntColors) {
    nav_background = other.nav_background
    nav_item = other.nav_item
    nav_item_focus = other.nav_item_focus
    nav_shadow = other.nav_shadow
}

internal val LocalColors = staticCompositionLocalOf { defaultAntColors() }