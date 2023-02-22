package fr.uge.plutus.layout.transaction_new

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import fr.uge.plutus.navigation.NavigationRoute
import fr.uge.plutus.ui.ant.Ant
import fr.uge.plutus.ui.components.*
import fr.uge.plutus.ui.input.AntDateInput
import fr.uge.plutus.ui.input.AntNoteInput

@Composable
fun TransactionNewScreen(
    navController: NavHostController,
    viewModel: TransactionNewViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val price = state.price
    val sheetVisible = remember { mutableStateOf(false) }

    AntBottomSheetScaffold(
        topBar = {
            AntTopBar(
                title = "Create transaction",
                leadingIcons = listOf {
                    AntActionButton(
                        icon = Icons.Outlined.ArrowBack,
                        onClick = {
                            navController.popBackStack()
                        }
                    )
                },
            )
        },
        sheetPeekHeight = 0.dp,
        sheetVisible = sheetVisible,
        sheetContent = {}
    ) {
        Column(
            modifier = Modifier.padding(horizontal = Ant.spacing.default),
            verticalArrangement = Arrangement.spacedBy(Ant.spacing.default)
        ) {
            Spacer(modifier = Modifier.size(Ant.spacing.default))
            AntAmountInput(
                amount = price,
                onClick = {
                    navController.navigate(NavigationRoute.Price.route)
                }
            )
            AntTagInput (
                onClick = {
                    navController.navigate(NavigationRoute.Tag.route)
                }
            )
            AntNoteInput (
                onClick = {
                    navController.navigate(NavigationRoute.Note.route)
                }
            )
            AntDateInput (
                onClick = {
                    navController.navigate(NavigationRoute.Date.route)
                }
            )
        }
    }
}

@Preview
@Composable
fun TransactionNewScreenPreview(

) {
    val navController = rememberNavController()
    TransactionNewScreen(
        navController = navController
    )
}