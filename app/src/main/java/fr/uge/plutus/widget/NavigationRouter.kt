package fr.uge.plutus.widget

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import fr.uge.plutus.layout.transaction_list.TransactionListScreen
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.AnimatedNavHost
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

    object Transaction: NavigationRoute(
        path = "transactions"
    )

}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavigationRouter(
    navController: NavHostController
) {
    Scaffold (
        bottomBar = {
            NavigationBar(
                navController = navController
            )
        }
    ) {
        AnimatedNavHost(
            navController = navController,
            startDestination = NavigationRoute.Home.path,
        ) {
            composable(
                route = NavigationRoute.Home.path,
                enterTransition = {
                    when(initialState.destination.route) {
                        NavigationRoute.Transaction.path -> slideInHorizontally(
                            initialOffsetX = { width -> -width },
                            animationSpec = tween(300)
                        )
                        else -> slideInVertically(
                            initialOffsetY = { height -> height/15 },
                            animationSpec = tween(300)
                        ) + fadeIn(
                            animationSpec = tween(150)
                        )
                    }
                },
                exitTransition = {
                    when(targetState.destination.route) {
                        NavigationRoute.Transaction.path -> slideOutHorizontally(
                            targetOffsetX = { width -> -width },
                            animationSpec = tween(300)
                        )
                        else -> {
                            slideOutVertically(
                                targetOffsetY = { height -> height/15 },
                                animationSpec = tween(300)
                            ) + fadeOut(
                                animationSpec = tween(150)
                            )
                        }
                    }
                },
                content = {
                    HomeScreen(
                        navController = navController
                    )
                }
            )
            composable(
                route = NavigationRoute.History.path,
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
                },
                content = {
                    TransactionListScreen(
                        navController = navController
                    )
                }
            )
            composable(
                route = NavigationRoute.Wallets.path,
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
                },
                content = {
                    WalletScreen(
                        navController = navController
                    )
                }
            )
            composable(
                route = NavigationRoute.Settings.path,
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
                },
                content = {
                    SettingScreen(
                        navController = navController
                    )
                }
            )
            composable(
                route = NavigationRoute.Transaction.path,
                enterTransition = {
                    slideInHorizontally(
                        initialOffsetX = { width -> width },
                        animationSpec = tween(300)
                    )
                },
                exitTransition = {
                    slideOutHorizontally(
                        targetOffsetX = { width -> width },
                        animationSpec = tween(300)
                    )
                },
                content = {
                    TransactionListScreen(
                        navController = navController
                    )
                }
            )
        }
    }
}
