package fr.uge.plutus.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import fr.uge.plutus.storage.LocalStorageProvider
import fr.uge.plutus.storage.LocalStorageViewModel
import fr.uge.plutus.ui.ant.Ant
import kotlinx.coroutines.launch

@Composable
fun SettingCard(
    label: String,
    desc: String = "",
    localStorage: LocalStorageViewModel = hiltViewModel()
) {

    val scope = rememberCoroutineScope()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = Ant.spacing.default,
                vertical = Ant.spacing.small
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Column {
            Text(
                text = label,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Ant.colors.primary_text,
            )
            Text(
                text = desc,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                color = Ant.colors.secondary_text
            )
        }
        Switch(
            colors = SwitchDefaults.colors(
                checkedThumbColor = Ant.colors.primary_color_5,
                checkedTrackColor = Ant.colors.primary_color_2,
                checkedTrackAlpha = 0.54f,
                uncheckedThumbColor = Ant.colors.gray_1,
                uncheckedTrackColor = Ant.colors.gray_6,
                uncheckedTrackAlpha = 0.38f
            ),
            checked = LocalStorageProvider.dark,
            onCheckedChange = {
                scope.launch {
                    localStorage.setDark(it)
                }
            }
        )
    }
}


@Preview
@Composable
fun SettingCardPreview(

) {
    val state  = remember { mutableStateOf(false) }
    SettingCard(
        label = "Dark mode",
        desc = "By default use system mode",
        //state = state
    )
}