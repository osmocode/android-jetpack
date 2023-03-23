package fr.uge.plutus.layout.budget_screen

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import fr.uge.plutus.data.model.Price
import fr.uge.plutus.layout.budget_screen.layout.AmountLayout
import fr.uge.plutus.layout.budget_screen.layout.DateLayout
import fr.uge.plutus.layout.budget_screen.layout.TagLayout
import fr.uge.plutus.navigation.*
import fr.uge.plutus.storage.SettingsWallet
import fr.uge.plutus.ui.ant.Ant
import fr.uge.plutus.ui.components.*
import fr.uge.plutus.ui.input.AntDateInput
import fr.uge.plutus.ui.input.AntTagInput

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BudgetScreen(
    navHostController: NavHostController,
    sheetVisible: MutableState<Boolean>,
    viewModel: BudgetViewModel = hiltViewModel()
) {
    val navController = rememberAnimatedNavController()

    AnimatedNavHost(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Ant.colors.background),
        navController = navController,
        enterTransition = enterTransition(),
        exitTransition = exitTransition(),
        popEnterTransition = popEnterTransition(),
        popExitTransition = popExitTransition(),
        startDestination = NavigationRoute.MainScreen.TransactionBudgetScreen.TransactionBudgetOverview.route
    ) {
        composable(
            route = NavigationRoute.MainScreen.TransactionBudgetScreen.TransactionBudgetOverview.route,
            content = {
                BudgetScreenOverview(
                    navHostController = navHostController,
                    navController = navController,
                    sheetVisible = sheetVisible,
                    viewModel = viewModel
                )
            }
        )
        composable(
            route = NavigationRoute.MainScreen.TransactionBudgetScreen.AmountLayout.route,
            content = {
                AmountLayout(
                    navController = navController,
                    viewModel = viewModel
                )
            }
        )
        composable(
            route = NavigationRoute.MainScreen.TransactionBudgetScreen.TagLayout.route,
            content = {
                TagLayout(
                    navController = navController,
                    viewModel = viewModel
                )
            }
        )
        composable(
            route = NavigationRoute.MainScreen.TransactionBudgetScreen.StartDateLayout.route,
            content = {
                DateLayout(
                    navController = navController,
                    viewModel = viewModel,
                    start = true
                )
            }
        )
        composable(
            route = NavigationRoute.MainScreen.TransactionBudgetScreen.EndDateLayout.route,
            content = {
                DateLayout(
                    navController = navController,
                    viewModel = viewModel,
                    start = false
                )
            }
        )
    }

}


@Composable
fun BudgetScreenOverview(
    navHostController: NavHostController,
    navController: NavHostController,
    sheetVisible: MutableState<Boolean>,
    viewModel: BudgetViewModel
) {
    val wallet = SettingsWallet.current

    AntBottomSheetScaffold(
        sheetPeekHeight = 0.dp,
        sheetVisible = sheetVisible,
        sheetContent = { /*TODO*/ },
        topBar = {
            AntTopBar(
                sheetVisible = sheetVisible,
                title = "add budget",
                leadingIcons = listOf {
                    AntActionButton(
                        icon = Icons.Outlined.ArrowBack,
                        onClick = {
                            navHostController.popBackStack()
                        }
                    )
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(Ant.spacing.default),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(Ant.spacing.default)
            ) {
                AntAmountInput(
                    price = Price(
                        currency = viewModel.state.value.budget.targetPrice.currency,
                        amount = viewModel.state.value.budget.targetPrice.amount
                    ),
                    onClick = { navController.navigate(route = NavigationRoute.MainScreen.TransactionBudgetScreen.AmountLayout.route) }
                )
                AntTagInput(
                    tags = viewModel.state.value.newTag,
                    onClick = { navController.navigate(route = NavigationRoute.MainScreen.TransactionBudgetScreen.TagLayout.route) }
                )
                AntDateInput(
                    timestamp = viewModel.state.value.budget.dateStart,
                    onClick = { navController.navigate(route = NavigationRoute.MainScreen.TransactionBudgetScreen.StartDateLayout.route) }
                )
                AntDateInput(
                    timestamp = viewModel.state.value.budget.dateEnd,
                    onClick = { navController.navigate(route = NavigationRoute.MainScreen.TransactionBudgetScreen.EndDateLayout.route) }
                )
            }
            CustomButton(
                title = "add budget",
                type = CustomButtonType.PRIMARY,
                onClick = {
                    viewModel.onEvent(BudgetEvent.BudgetSubmit(wallet = wallet))
                    navHostController.popBackStack()
                }
            )
        }
    }

}
