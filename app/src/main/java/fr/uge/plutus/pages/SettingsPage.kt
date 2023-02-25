package fr.uge.plutus.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Light
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import fr.uge.plutus.storage.LocalStorage
import fr.uge.plutus.ui.ant.Ant
import fr.uge.plutus.ui.components.*

@Composable
fun SettingsPage(
    navBarController: MutableState<Boolean>,
    navController: NavHostController
) {

    val sheetVisible = remember { mutableStateOf(false) }
    val currentBottomSheet = remember { mutableStateOf("default") }

    LaunchedEffect(sheetVisible.value) {
        navBarController.value = !sheetVisible.value
    }

    AntBottomSheetScaffold(
        topBar = {
            AntTopBar(
                title = "Settings"
            )
        },
        sheetPeekHeight = 0.dp,
        sheetVisible = sheetVisible,
        sheetContent = {
            SettingsPageBottomSheet(
                key = currentBottomSheet.value,
                onFinish = {
                    sheetVisible.value = false
                }
            )
        }
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(Ant.spacing.small)
        ){
            SettingCard(
                label = "Dark mode",
                desc = "By default using system",
                onClick = {
                    sheetVisible.value = true
                    currentBottomSheet.value = "theme"
                }
            )
        }
    }
}

@Composable
fun SettingsPageBottomSheet(
    key: String,
    onFinish: () -> Unit
) {
    val items = HashMap<String, @Composable () -> Unit>()
    val localStorage = hiltViewModel<LocalStorage>()

    items["theme"] = {
        Column(
            modifier = Modifier
                .padding(Ant.spacing.default)
                .background(
                    color = Ant.colors.gray_1,
                    shape = Ant.shapes.default
                ),
            verticalArrangement = Arrangement.spacedBy(Ant.spacing.small)
        ) {
            AntActionCard(
                icon = Icons.Default.Settings,
                label = "Default",
                desc = "Select default mode of your mobile",
                onClick = {
                    onFinish.invoke()
                    localStorage.setDark(null)
                }
            )
            AntActionCard(
                icon = Icons.Default.Light,
                label = "Light",
                desc = "Select to change light mode",
                onClick = {
                    onFinish.invoke()
                    localStorage.setDark(false)
                }
            )
            AntActionCard(
                icon = Icons.Default.DarkMode,
                label = "Dark",
                desc = "Select to change dark mode",
                onClick = {
                    onFinish.invoke()
                    localStorage.setDark(true)
                }
            )
        }
    }

    items.getOrDefault(key) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "Error pleas go back..."
            )
        }
    }.invoke()

}
