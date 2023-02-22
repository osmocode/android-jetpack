package fr.uge.plutus.ui.ant

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color


@Stable
class AntColors(
    gray_1: Color,
    gray_2: Color,
    gray_3: Color,
    gray_4: Color,
    gray_5: Color,
    gray_6: Color,
    gray_7: Color,
    gray_8: Color,
    gray_9: Color,
    gray_10: Color,
    gray_11: Color,
    gray_12: Color,
    gray_13: Color,

    primary_color_1: Color,
    primary_color_2: Color,
    primary_color_3: Color,
    primary_color_4: Color,
    primary_color_5: Color,

    title: Color,
    primary_text: Color,
    secondary_text: Color,
    disable: Color,
    border: Color,
    dividers: Color,
    background: Color,

) {
    var gray_1 by mutableStateOf(gray_1, structuralEqualityPolicy())
        internal set
    var gray_2 by mutableStateOf(gray_2, structuralEqualityPolicy())
        internal set
    var gray_3 by mutableStateOf(gray_3, structuralEqualityPolicy())
        internal set
    var gray_4 by mutableStateOf(gray_4, structuralEqualityPolicy())
        internal set
    var gray_5 by mutableStateOf(gray_5, structuralEqualityPolicy())
        internal set
    var gray_6 by mutableStateOf(gray_6, structuralEqualityPolicy())
        internal set
    var gray_7 by mutableStateOf(gray_7, structuralEqualityPolicy())
        internal set
    var gray_8 by mutableStateOf(gray_8, structuralEqualityPolicy())
        internal set
    var gray_9 by mutableStateOf(gray_9, structuralEqualityPolicy())
        internal set
    var gray_10 by mutableStateOf(gray_10, structuralEqualityPolicy())
        internal set
    var gray_11 by mutableStateOf(gray_10, structuralEqualityPolicy())
        internal set
    var gray_12 by mutableStateOf(gray_10, structuralEqualityPolicy())
        internal set
    var gray_13 by mutableStateOf(gray_10, structuralEqualityPolicy())
        internal set

    var primary_color_1 by mutableStateOf(primary_color_1, structuralEqualityPolicy())
        internal set
    var primary_color_2 by mutableStateOf(primary_color_2, structuralEqualityPolicy())
        internal set
    var primary_color_3 by mutableStateOf(primary_color_3, structuralEqualityPolicy())
        internal set
    var primary_color_4 by mutableStateOf(primary_color_4, structuralEqualityPolicy())
        internal set
    var primary_color_5 by mutableStateOf(primary_color_5, structuralEqualityPolicy())
        internal set

    var title by mutableStateOf(title, structuralEqualityPolicy())
        internal set
    var primary_text by mutableStateOf(primary_text, structuralEqualityPolicy())
        internal set
    var secondary_text by mutableStateOf(secondary_text, structuralEqualityPolicy())
        internal set
    var disable by mutableStateOf(disable, structuralEqualityPolicy())
        internal set
    var border by mutableStateOf(border, structuralEqualityPolicy())
        internal set
    var dividers by mutableStateOf(dividers, structuralEqualityPolicy())
        internal set
    var background by mutableStateOf(background, structuralEqualityPolicy())
        internal set

    fun copy(
        gray_1: Color = this.gray_1,
        gray_2: Color = this.gray_2,
        gray_3: Color = this.gray_3,
        gray_4: Color = this.gray_3,
        gray_5: Color = this.gray_4,
        gray_6: Color = this.gray_5,
        gray_7: Color = this.gray_6,
        gray_8: Color = this.gray_7,
        gray_9: Color = this.gray_8,
        gray_10: Color = this.gray_10,
        gray_11: Color = this.gray_11,
        gray_12: Color = this.gray_12,
        gray_13: Color = this.gray_13,

        primary_color_1: Color = this.primary_color_1,
        primary_color_2: Color = this.primary_color_2,
        primary_color_3: Color = this.primary_color_3,
        primary_color_4: Color = this.primary_color_4,
        primary_color_5: Color = this.primary_color_5,

        title: Color = this.title,
        primary_text: Color = this.primary_text,
        secondary_text: Color = this.secondary_text,
        disable: Color = this.disable,
        border: Color = this.border,
        dividers: Color = this.dividers,
        background: Color = this.background,
    ): AntColors = AntColors(
        gray_1,
        gray_2,
        gray_3,
        gray_4,
        gray_5,
        gray_6,
        gray_7,
        gray_8,
        gray_9,
        gray_10,
        gray_11,
        gray_12,
        gray_13,

        primary_color_1,
        primary_color_2,
        primary_color_3,
        primary_color_4,
        primary_color_5,

        title,
        primary_text,
        secondary_text,
        disable,
        border,
        dividers,
        background
    )

}

fun defaultAntColors(
    gray_1: Color = Color(0xFFFFFFFF),
    gray_2: Color = Color(0xFFFAFAFA),
    gray_3: Color = Color(0xFFF5F5F5),
    gray_4: Color = Color(0xFFF0F0F0),
    gray_5: Color = Color(0xFFD9D9D9),
    gray_6: Color = Color(0xFFBFBFBF),
    gray_7: Color = Color(0xFF8C8C8C),
    gray_8: Color = Color(0xFF595959),
    gray_9: Color = Color(0xFF434343),
    gray_10: Color = Color(0xFF262626),
    gray_11: Color = Color(0xFF1F1F1F),
    gray_12: Color = Color(0xFF141414),
    gray_13: Color = Color(0xFF000000),


    primary_color_1: Color = Color(0xFFE6F4FF),
    primary_color_2: Color = Color(0xFFB0DBFF),
    primary_color_3: Color = Color(0xFF87C5FF),
    primary_color_4: Color = Color(0xFF5EACFF),
    primary_color_5: Color = Color(0xFF3690FF),

    title: Color = Color(0xFF252525),
    primary_text: Color = Color(0xFF252525),
    secondary_text: Color = Color(0xFF8C8C8C),
    disable: Color = Color(0xFFBFBFBF),
    border: Color = Color(0xFFD9D9D9),
    dividers: Color = Color(0xFFF0F0F0),
    background: Color = Color(0xFFF5F5F5),

) = AntColors(
    gray_1,
    gray_2,
    gray_3,
    gray_4,
    gray_5,
    gray_6,
    gray_7,
    gray_8,
    gray_9,
    gray_10,
    gray_11,
    gray_12,
    gray_13,

    primary_color_1,
    primary_color_2,
    primary_color_3,
    primary_color_4,
    primary_color_5,

    title,
    primary_text,
    secondary_text,
    disable,
    border,
    dividers,
    background
)

internal fun AntColors.updateColorsFrom(other: AntColors) {
    gray_1 = other.gray_1
    gray_2 = other.gray_2
    gray_3 = other.gray_3
    gray_4 = other.gray_4
    gray_5 = other.gray_5
    gray_6 = other.gray_6
    gray_7 = other.gray_7
    gray_8 = other.gray_8
    gray_9 = other.gray_9
    gray_10 = other.gray_10
    gray_11 = other.gray_11
    gray_12 = other.gray_12
    gray_13 = other.gray_13

    primary_color_1 = other.primary_color_1
    primary_color_2 = other.primary_color_2
    primary_color_3 = other.primary_color_3
    primary_color_4 = other.primary_color_4
    primary_color_5 = other.primary_color_5

    title = other.title
    primary_text = other.primary_text
    secondary_text = other.secondary_text
    disable = other.disable
    border = other.border
    dividers = other.dividers
    background = other.background
}

internal val LocalColors = staticCompositionLocalOf { defaultAntColors() }