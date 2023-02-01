package fr.uge.plutus.widget

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import fr.uge.plutus.screen.HistoryScreen
import fr.uge.plutus.screen.HomeScreen
import fr.uge.plutus.screen.SettingScreen
import fr.uge.plutus.screen.WalletScreen


sealed class NavigationRoute(
    val path: String,
) {
    object Home: NavigationRoute (
        path = "home"
    )

    object History: NavigationRoute (
        path = "history"
    )

    object Wallets: NavigationRoute (
        path = "wallets"
    )

    object Settings: NavigationRoute (
        path = "settings"
    )
}

@Composable
fun NavigationRouter(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = NavigationRoute.Home.path,
    ) {
        composable(
            route = NavigationRoute.Home.path,
            content = {
                HomeScreen(
                    navController = navController
                )
            }
        )
        composable(
            route = NavigationRoute.History.path,
            content = {
                HistoryScreen(
                    navController = navController
                )
            }
        )
        composable(
            route = NavigationRoute.Wallets.path,
            content = {
                WalletScreen(
                    navController = navController
                )
            }
        )
        composable(
            route = NavigationRoute.Settings.path,
            content = {
                SettingScreen(
                    navController = navController
                )
            }
        )
    }
}
