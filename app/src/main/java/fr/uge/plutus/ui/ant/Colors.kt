package fr.uge.plutus.ui.ant

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color


@Stable
class AntColors(
    nav_background: Color,
    nav_item: Color,
    nav_item_focus: Color,
    nav_shadow: Color,
    input_textColor: Color,
    input_leadingIconColor: Color,
    input_trailingIconColor: Color,
    input_cursorColor: Color,
    input_placeholderColor: Color,
    input_focusedIndicatorColor: Color,
    input_unfocusedIndicatorColor: Color,
    input_backgroundColor: Color
) {
    var nav_background by mutableStateOf(nav_background, structuralEqualityPolicy())
        internal set

    var nav_item by mutableStateOf(nav_item, structuralEqualityPolicy())
        internal set

    var nav_item_focus by mutableStateOf(nav_item_focus, structuralEqualityPolicy())
        internal set

    var nav_shadow by mutableStateOf(nav_shadow, structuralEqualityPolicy())
        internal set

    var input_textColor by mutableStateOf(input_textColor, structuralEqualityPolicy())
        internal set

    var input_leadingIconColor by mutableStateOf(input_leadingIconColor, structuralEqualityPolicy())
        internal set

    var input_trailingIconColor by mutableStateOf(input_trailingIconColor, structuralEqualityPolicy())
        internal set

    var input_cursorColor by mutableStateOf(input_cursorColor, structuralEqualityPolicy())
        internal set

    var input_placeholderColor by mutableStateOf(input_placeholderColor, structuralEqualityPolicy())
        internal set

    var input_focusedIndicatorColor by mutableStateOf(input_focusedIndicatorColor, structuralEqualityPolicy())
        internal set

    var input_unfocusedIndicatorColor by mutableStateOf(input_unfocusedIndicatorColor, structuralEqualityPolicy())
        internal set

    var input_backgroundColor by mutableStateOf(input_backgroundColor, structuralEqualityPolicy())
        internal set

    fun copy(
        nav_background: Color = this.nav_background,
        nav_item_color: Color = this.nav_item,
        nav_item_focus: Color = this.nav_item_focus,
        nav_shadow: Color = this.nav_shadow,
        input_textColor: Color = this.input_textColor,
        input_leadingIconColor: Color = this.input_leadingIconColor,
        input_trailingIconColor: Color = this.input_trailingIconColor,
        input_cursorColor: Color = this.input_cursorColor,
        input_placeholderColor: Color = this.input_placeholderColor,
        input_focusedIndicatorColor: Color = this.input_focusedIndicatorColor,
        input_unfocusedIndicatorColor: Color = this.input_unfocusedIndicatorColor,
        input_backgroundColor: Color = this.input_backgroundColor
    ): AntColors = AntColors(
        nav_background,
        nav_item_color,
        nav_item_focus,
        nav_shadow,
        input_textColor,
        input_leadingIconColor,
        input_trailingIconColor,
        input_cursorColor,
        input_placeholderColor,
        input_focusedIndicatorColor,
        input_unfocusedIndicatorColor,
        input_backgroundColor
    )
}

fun defaultAntColors(
    nav_background: Color = Color(0xFFECF0F1),
    nav_item: Color = Color(0xFF95A5A6),
    nav_item_focus: Color = Color(0xFF2C3E50),
    nav_shadow: Color = Color(0xFF000000),
    input_textColor: Color = Color(0xFF2C3E50),
    input_leadingIconColor: Color = Color(0xFF2C3E50),
    input_trailingIconColor: Color = Color(0xFF2C3E50),
    input_cursorColor: Color = Color(0xFF2C3E50),
    input_placeholderColor: Color = Color(0xFF2C3E50),
    input_focusedIndicatorColor: Color = Color.Transparent,
    input_unfocusedIndicatorColor: Color = Color.Transparent,
    input_backgroundColor: Color = Color(0xFFECF0F1)
) = AntColors(
    nav_background,
    nav_item,
    nav_item_focus,
    nav_shadow,
    input_textColor,
    input_leadingIconColor,
    input_trailingIconColor,
    input_cursorColor,
    input_placeholderColor,
    input_focusedIndicatorColor,
    input_unfocusedIndicatorColor,
    input_backgroundColor
)

internal fun AntColors.updateColorsFrom(other: AntColors) {
    nav_background = other.nav_background
    nav_item = other.nav_item
    nav_item_focus = other.nav_item_focus
    nav_shadow = other.nav_shadow
    input_textColor = other.input_textColor
    input_leadingIconColor = other.input_leadingIconColor
    input_trailingIconColor = other.input_trailingIconColor
    input_cursorColor = other.input_cursorColor
    input_placeholderColor = other.input_placeholderColor
    input_focusedIndicatorColor = other.input_focusedIndicatorColor
    input_unfocusedIndicatorColor = other.input_unfocusedIndicatorColor
    input_backgroundColor = other.input_backgroundColor
}

internal val LocalColors = staticCompositionLocalOf { defaultAntColors() }