package fr.uge.plutus.layout.home_screen.layout

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import fr.uge.plutus.layout.home_screen.HomeViewModel
import fr.uge.plutus.navigation.NavigationRoute
import fr.uge.plutus.storage.SettingsWallet
import fr.uge.plutus.ui.components.DonutChartItem
import fr.uge.plutus.widget.BudgetWidget

@Composable
fun TransferLayout(
    navController: NavHostController,
    viewModel: HomeViewModel
) {
    val wallet = SettingsWallet.current

    BudgetWidget(
        title = "Transfer",
        data = viewModel.state.value.budgetsTransfer.map { budgetStatus ->
            DonutChartItem(
                label = budgetStatus.label,
                current = budgetStatus.current,
                target = budgetStatus.target,
                id = budgetStatus.budgetId
            )
        },
        add = {
            navController.navigate(
                route = NavigationRoute.MainScreen.TransactionBudgetScreen.createBudgetTransfer(
                    wallet
                )
            )
        }
    )

}