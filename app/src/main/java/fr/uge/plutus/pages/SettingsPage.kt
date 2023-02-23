package fr.uge.plutus.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fr.uge.plutus.ui.ant.Ant
import fr.uge.plutus.ui.components.AntBottomSheetScaffold
import fr.uge.plutus.ui.components.AntTopBar
import fr.uge.plutus.ui.components.SettingCard

@Composable
fun SettingsPage(
    navController: NavHostController
) {

    val sheetVisible = remember { mutableStateOf(false) }

    AntBottomSheetScaffold(
        topBar = {
            AntTopBar(title = "Settings",)
        },
        sheetPeekHeight = 0.dp,
        sheetVisible = sheetVisible,
        sheetContent = {}
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(Ant.spacing.small)
        ){
            SettingCard(
                label = "Dark mode",
                desc = "By default using system",
            )
        }
    }
}