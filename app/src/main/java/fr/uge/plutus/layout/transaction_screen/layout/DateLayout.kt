package fr.uge.plutus.layout.transaction_screen.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fr.uge.plutus.layout.transaction_screen.TransactionViewModel
import fr.uge.plutus.ui.ant.Ant
import fr.uge.plutus.ui.components.AntActionButton
import fr.uge.plutus.ui.components.AntBottomSheetScaffold
import fr.uge.plutus.ui.components.AntTopBar

@Composable
fun DateLayout(
    navController: NavHostController,
    sheetVisible: MutableState<Boolean> = remember { mutableStateOf(false) },
    viewModel: TransactionViewModel
) {

    AntBottomSheetScaffold(
        sheetPeekHeight = 0.dp,
        sheetVisible = sheetVisible,
        sheetContent = { /*TODO*/ },
        topBar = {
            AntTopBar(
                title = "Enter date",
                leadingIcons = listOf {
                    AntActionButton(
                        icon = Icons.Outlined.ArrowBack,
                        onClick = { navController.popBackStack() }
                    )
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(Ant.spacing.default)
        ) {
            Text("Hello")
        }
    }
}