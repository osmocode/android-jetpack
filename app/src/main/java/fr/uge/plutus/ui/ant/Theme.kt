package fr.uge.plutus.ui.ant

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import fr.uge.plutus.storage.SettingsDark

private val LightAntColors = defaultAntColors()

private val DarkAntColors = defaultAntColors().copy(
    gray_1 = Color(0xFF1F1F1F),
    gray_2 = Color(0xFF262626),
    gray_3 = Color(0xFF434343),
    gray_4 = Color(0xFF595959),
    gray_5 = Color(0xFF595959),
    gray_6 = Color(0xFF8C8C8C),
    gray_7 = Color(0xFF8C8C8C),
    gray_8 = Color(0xFFBFBFBF),
    gray_9 = Color(0xFFD9D9D9),
    gray_10 = Color(0xFFF0F0F0),
    gray_11 = Color(0xFFF5F5F5),
    gray_12 = Color(0xFFFAFAFA),
    gray_13 = Color(0xFFFFFFFF),


    primary_color_1 = Color(0xFFE6F4FF),
    primary_color_2 = Color(0xFFB0DBFF),
    primary_color_3 = Color(0xFF87C5FF),
    primary_color_4 = Color(0xFF5EACFF),
    primary_color_5 = Color(0xFF3690FF),

    title = Color(0xFFDBDBDB),
    primary_text = Color(0xFFDADADA),
    secondary_text = Color(0xFF7C7C7C),
    disable = Color(0xFF5A5A5A),
    border = Color(0xFF424242),
    dividers = Color(0xFF303030),
    background = Color(0xFF000000),
)


@Composable
fun AntTheme(
    content: @Composable () -> Unit,
) {
    Ant(
        colors = if (SettingsDark.current) DarkAntColors else LightAntColors,
        content = content
    )
}