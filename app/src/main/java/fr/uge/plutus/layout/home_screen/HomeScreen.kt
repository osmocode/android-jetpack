package fr.uge.plutus.layout.home_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import fr.uge.plutus.layout.home_screen.layout.ExpendLayout
import fr.uge.plutus.layout.home_screen.layout.HomeLayout
import fr.uge.plutus.layout.home_screen.layout.IncomingLayout
import fr.uge.plutus.layout.home_screen.layout.TransferLayout
import fr.uge.plutus.ui.components.*

@Composable
fun HomeScreen(
    navController: NavHostController,
    sheetVisible: MutableState<Boolean> = mutableStateOf(false),
    viewModel: HomeViewModel = hiltViewModel()
) {
    AntBottomSheetScaffold(
        sheetPeekHeight = 0.dp,
        sheetVisible = sheetVisible,
        sheetContent = {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "In progress")
            }
        },
        topBar = {
            AntTopBar(
                sheetVisible = sheetVisible,
                trailingIcons = listOf {
                    AntActionButton(
                        icon = Icons.Outlined.Notifications,
                        onClick = {
                            sheetVisible.value = !sheetVisible.value
                        }
                    )
                }
            )
        }
    ) {
        AntScrollablePagerLayout(
            pages = listOf(
                ScrollablePagerLayoutItem(
                    label = "Home",
                    content = {
                        HomeLayout(
                            navController = navController,
                            viewModel = viewModel
                        )
                    }
                ),
                ScrollablePagerLayoutItem(
                    label = "Incoming",
                    content = { IncomingLayout() }
                ),
                ScrollablePagerLayoutItem(
                    label = "Expend",
                    content = { ExpendLayout() }
                ),
                ScrollablePagerLayoutItem(
                    label = "Transfer",
                    content = { TransferLayout() }
                )
            )
        )
    }
}