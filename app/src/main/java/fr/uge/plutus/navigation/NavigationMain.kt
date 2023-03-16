package fr.uge.plutus.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.FindInPage
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import fr.uge.plutus.layout.budget_screen.BudgetScreen
import fr.uge.plutus.layout.transaction_screen.TransactionScreen
import fr.uge.plutus.layout.home_screen.HomeScreen
import fr.uge.plutus.layout.settings_screen.SettingsScreen
import fr.uge.plutus.layout.transactions_screen.TransactionsScreen
import fr.uge.plutus.layout.wallet_screen.WalletScreen
import fr.uge.plutus.storage.StorageViewModel
import fr.uge.plutus.ui.ant.Ant
import fr.uge.plutus.ui.components.*

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavigationMain(
    navHostController: NavHostController,
    storageViewModel: StorageViewModel = hiltViewModel()
) {
    val bottomBarVisible = remember { mutableStateOf(true) }
    val sheetVisible = remember { mutableStateOf(false) }
    val wallet = remember { mutableStateOf(-1) }
    val navController = rememberAnimatedNavController()

    LaunchedEffect(storageViewModel.state.value.wallet) {
        if (storageViewModel.ready.value) {
            val w = storageViewModel.state.value.wallet
            if (w == null || w == -1) {
                navController.popBackStack(
                    destinationId = navController.graph.findStartDestination().id,
                    inclusive = true
                )
                navHostController.popBackStack()
                navHostController.navigate(route = NavigationRoute.WalletScreen.route)
            } else {
                wallet.value = w
            }
        }
    }
    LaunchedEffect(sheetVisible.value) {
        bottomBarVisible.value = !sheetVisible.value
    }
    /* MAIN SCAFFOLD */
    Scaffold(
        /* BOTTOM NAVIGATION BAR */
        bottomBar = {
            AnimatedVisibility(
                visible = bottomBarVisible.value,
                enter = slideInVertically(
                    initialOffsetY = { height -> height * 2 / 3 },
                    animationSpec = tween(300)
                ),
                exit = slideOutVertically(
                    targetOffsetY = { height -> height * 2 / 3 },
                    animationSpec = tween(300)
                ),
                content = {
                    AntBottomBar(
                        bottomBarVisible = bottomBarVisible,
                        navController = navController,
                        items = listOf(
                            AntBottomBarItem(
                                route = NavigationRoute.MainScreen.HomeScreen.route,
                                title = "Home",
                                icon = Icons.Outlined.Home,
                                icon_focus = Icons.Filled.Home
                            ),
                            AntBottomBarItem(
                                route = NavigationRoute.MainScreen.SearchScreen.route,
                                title = "Search",
                                icon = Icons.Outlined.FindInPage,
                                icon_focus = Icons.Filled.FindInPage
                            ),
                            AntBottomBarItem(
                                route = NavigationRoute.MainScreen.WalletScreen.route,
                                title = "Wallets",
                                icon = Icons.Outlined.AccountBalanceWallet,
                                icon_focus = Icons.Filled.AccountBalanceWallet
                            ),
                            AntBottomBarItem(
                                route = NavigationRoute.MainScreen.SettingsScreen.route,
                                title = "Settings",
                                icon = Icons.Outlined.Settings,
                                icon_focus = Icons.Filled.Settings
                            )
                        )
                    )
                }
            )
        }
    ) {
        /* PAGE CONTENT */
        AnimatedNavHost(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Ant.colors.background),
            navController = navController,
            enterTransition = enterTransition(),
            exitTransition = exitTransition(),
            popEnterTransition = popEnterTransition(),
            popExitTransition = popExitTransition(),
            startDestination = NavigationRoute.MainScreen.HomeScreen.route
        ) {
            composable(
                route = NavigationRoute.MainScreen.HomeScreen.route,
                enterTransition = { navEnterTransition(initialState) },
                exitTransition = { navExitTransition(targetState) },
                content = {
                    HomeScreen(
                        navController = navController,
                        sheetVisible = sheetVisible
                    )
                }
            )
            composable(
                route = NavigationRoute.MainScreen.SearchScreen.route,
                enterTransition = { navEnterTransition(initialState) },
                exitTransition = { navExitTransition(targetState) },
                content = {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Text(
                            modifier = Modifier.align(Alignment.Center),
                            text = "Search Layout"
                        )
                    }
                }
            )
            composable(
                route = NavigationRoute.MainScreen.WalletScreen.route,
                enterTransition = { navEnterTransition(initialState) },
                exitTransition = { navExitTransition(targetState) },
                content = {
                    WalletScreen(
                        sheetVisible = sheetVisible
                    )
                }
            )
            composable(
                route = NavigationRoute.MainScreen.SettingsScreen.route,
                enterTransition = { navEnterTransition(initialState) },
                exitTransition = { navExitTransition(targetState) },
                content = {
                    SettingsScreen(
                        sheetVisible = sheetVisible
                    )
                }
            )
            composable(
                route = NavigationRoute.MainScreen.TransactionsScreen.route,
                arguments = listOf(
                    navArgument(name = "wallet") { type = NavType.IntType }
                ),
                content = {
                    TransactionsScreen(
                        navController = navController,
                        sheetVisible = sheetVisible
                    )
                }
            )
            composable(
                route = NavigationRoute.MainScreen.TransactionScreen.route,
                arguments = listOf(
                    navArgument(name = "action") { type = NavType.StringType },
                    navArgument(name = "type") { type = NavType.StringType },
                    navArgument(name = "id") { type = NavType.IntType; defaultValue = -1 }
                ),
                content = {
                    TransactionScreen(
                        navHostController = navController,
                        sheetVisible = sheetVisible
                    )
                }
            )
            composable(
                route = NavigationRoute.MainScreen.TransactionBudgetScreen.route,
                arguments = listOf(
                    navArgument(name = "wallet") { type = NavType.IntType },
                    navArgument(name = "type") { type = NavType.StringType }
                ),
                content = {
                    BudgetScreen(
                        navHostController = navController,
                        sheetVisible = sheetVisible
                    )
                }
            )
        }
    }
}