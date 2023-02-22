package fr.uge.plutus.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.More
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fr.uge.plutus.ui.ant.Ant
import fr.uge.plutus.ui.components.AntActionButton
import fr.uge.plutus.ui.components.AntBottomSheetScaffold
import fr.uge.plutus.ui.components.AntTopBar
import fr.uge.plutus.ui.components.SettingCard

@Composable
fun SettingsPage(
    darkMode: MutableState<Boolean>,
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
                state = darkMode
            )
        }
    }
}