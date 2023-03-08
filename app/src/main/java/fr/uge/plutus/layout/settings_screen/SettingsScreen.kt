package fr.uge.plutus.layout.settings_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Light
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import fr.uge.plutus.ui.ant.Ant
import fr.uge.plutus.ui.components.AntActionCard
import fr.uge.plutus.ui.components.AntBottomSheetScaffold
import fr.uge.plutus.ui.components.AntTopBar


@Composable
fun SettingsScreen(
    sheetVisible: MutableState<Boolean> = remember { mutableStateOf(false) },
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val settings = remember { mutableStateOf("default") }

    AntBottomSheetScaffold(
        sheetPeekHeight = 0.dp,
        sheetVisible = sheetVisible,
        sheetContent = {
            Column(
                modifier = Modifier
                    .padding(Ant.spacing.default)
                    .clip(Ant.shapes.default)
                    .background(color = Ant.colors.gray_1),
                verticalArrangement = Arrangement.spacedBy(Ant.spacing.small)
            ) {
                when (settings.value) {
                    "dark" -> {
                        AntActionCard(
                            icon = Icons.Default.Settings,
                            label = "Default",
                            desc = "Select default mode of your mobile",
                            onClick = {
                                viewModel.onEvent(SettingsEvent.SetDarkTheme(null))
                                sheetVisible.value = false
                            }
                        )
                        AntActionCard(
                            icon = Icons.Default.Light,
                            label = "Light",
                            desc = "Select to change light mode",
                            onClick = {
                                viewModel.onEvent(SettingsEvent.SetDarkTheme(false))
                                sheetVisible.value = false
                            }
                        )
                        AntActionCard(
                            icon = Icons.Default.DarkMode,
                            label = "Dark",
                            desc = "Select to change dark mode",
                            onClick = {
                                viewModel.onEvent(SettingsEvent.SetDarkTheme(true))
                                sheetVisible.value = false
                            }
                        )
                    }
                }
            }
        },
        topBar = {
            AntTopBar(
                title = "Settings",
                sheetVisible = sheetVisible
            )
        }
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(Ant.spacing.default)
        ) {
            SettingsScreenCard(
                label = "Dark mode",
                desc = "By default using system",
                onClick = {
                    sheetVisible.value = true
                    settings.value = "dark"
                }
            )
        }
    }
}

@Composable
fun SettingsScreenCard(
    label: String,
    desc: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(horizontal = Ant.spacing.small)
            .fillMaxWidth()
            .clip(Ant.shapes.default)
            .clickable(onClick = onClick)
            .padding(Ant.spacing.default),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
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
    }
}
