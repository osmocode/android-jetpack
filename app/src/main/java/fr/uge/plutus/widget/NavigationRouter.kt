package fr.uge.plutus.widget

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import fr.uge.plutus.layout.transaction_list.TransactionListScreen
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.AnimatedNavHost
import fr.uge.plutus.screen.HistoryScreen
import fr.uge.plutus.screen.HomeScreen
import fr.uge.plutus.screen.SettingScreen
import fr.uge.plutus.screen.WalletScreen


sealed class NavigationRoute(
    val path: String,
) {
    object Home : NavigationRoute(
        path = "home"
    )

    object History : NavigationRoute(
        path = "history"
    )

    object Wallets : NavigationRoute(
        path = "wallets"
    )

    object Settings : NavigationRoute(
        path = "settings"
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavigationRouter(
    navController: NavHostController
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = NavigationRoute.Home.path,
        enterTransition = {
            slideInVertically(
                initialOffsetY = { height -> height/15 },
                animationSpec = tween(300)
            ) + fadeIn(
                animationSpec = tween(150)
            )
        },
        exitTransition = {
            slideOutVertically(
                targetOffsetY = { height -> height/15 },
                animationSpec = tween(300)
            ) + fadeOut(
                animationSpec = tween(150)
            )
        }
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
                TransactionListScreen(
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
