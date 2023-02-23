package fr.uge.plutus.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import fr.uge.plutus.layout.transaction_list.TransactionListScreen
import fr.uge.plutus.layout.transaction_new.TransactionNewScreen
import fr.uge.plutus.pages.*
import fr.uge.plutus.ui.ant.Ant


sealed class NavigationRoute(
    val route: String,
) {
    object Home : NavigationRoute(route = "home")
    object Research : NavigationRoute(route = "research")
    object Wallets : NavigationRoute(route = "wallets")
    object Settings : NavigationRoute(route = "settings")
    object Transaction : NavigationRoute(route = "transactions")
    object NewTransaction : NavigationRoute(route = "newTransaction")
    object Price : NavigationRoute(route = "price")
    object Tag : NavigationRoute(route = "tag")
    object TitleAndDescription : NavigationRoute(route = "titleAndDescription")
    object Date : NavigationRoute(route = "date")
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavigationRouter(
    darkMode: MutableState<Boolean>,
    navController: NavHostController
) {
    Scaffold(
        bottomBar = {
            NavigationBar(
                navController = navController
            )
        }
    ) {
        NavigationRouteHost(
            navController = navController,
            startDestination = NavigationRoute.Home.route
        ) {
            composable(
                route = NavigationRoute.Home.route,
                enterTransition = { navigationRouterEnterAnimation(initialState) },
                exitTransition = { navigationRouterExitAnimation(targetState) },
                content = {
                    HomePage(navController = navController)
                }
            )
            composable(
                route = NavigationRoute.Research.route,
                enterTransition = { navigationRouterEnterAnimation(initialState) },
                exitTransition = { navigationRouterExitAnimation(targetState) },
                content = {
                    ResearchPage(navController = navController)
                }
            )
            composable(
                route = NavigationRoute.Wallets.route,
                enterTransition = { navigationRouterEnterAnimation(initialState) },
                exitTransition = { navigationRouterExitAnimation(targetState) },
                content = {
                    HomePage(navController = navController)
                }
            )
            composable(
                route = NavigationRoute.Settings.route,
                enterTransition = { navigationRouterEnterAnimation(initialState) },
                exitTransition = { navigationRouterExitAnimation(targetState) },
                content = {
                    SettingsPage(
                        darkMode = darkMode,
                        navController = navController
                    )
                }
            )
            composable(
                route = NavigationRoute.Transaction.route,
                content = {
                    TransactionListScreen(
                        navController = navController
                    )
                }
            )
            composable(
                route = NavigationRoute.NewTransaction.route + "?transactionId={transactionId}",
                arguments = listOf(
                    navArgument(
                        name = "transactionId"
                    ) {
                        type = NavType.IntType
                        defaultValue = -1
                    }
                ),
                content = {
                    TransactionNewScreen(
                        navController = navController
                    )
                }
            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavigationRouteHost(
    navController: NavHostController,
    startDestination: String,
    content: NavGraphBuilder.() -> Unit
) {
    AnimatedNavHost(
        modifier = Modifier.background(color = Ant.colors.background),
        navController = navController,
        startDestination = startDestination,
        enterTransition = {
            slideInHorizontally(
                initialOffsetX = { width -> width },
                animationSpec = tween(300)
            )
        },
        exitTransition = {
            slideOutHorizontally(
                targetOffsetX = { width -> -width },
                animationSpec = tween(300)
            )
        },
        popEnterTransition = {
            slideInHorizontally(
                initialOffsetX = { width -> -width },
                animationSpec = tween(300)
            )
        },
        popExitTransition = {
            slideOutHorizontally(
                targetOffsetX = { width -> width },
                animationSpec = tween(300)
            )
        },
    ) {
        content()
    }
}

fun navigationRouterEnterAnimation(
    initialState: NavBackStackEntry
): EnterTransition? {
    return when (initialState.destination.route) {
        NavigationRoute.Home.route,
        NavigationRoute.Research.route,
        NavigationRoute.Wallets.route,
        NavigationRoute.Settings.route -> slideInVertically(
            initialOffsetY = { height -> height / 15 },
            animationSpec = tween(300)
        ) + fadeIn(
            animationSpec = tween(150)
        )
        else -> null
    }
}

fun navigationRouterExitAnimation(
    targetState: NavBackStackEntry
): ExitTransition? {
    return when (targetState.destination.route) {
        NavigationRoute.Home.route,
        NavigationRoute.Research.route,
        NavigationRoute.Wallets.route,
        NavigationRoute.Settings.route -> slideOutVertically(
            targetOffsetY = { height -> height / 15 },
            animationSpec = tween(300)
        ) + fadeOut(
            animationSpec = tween(150)
        )
        else -> null
    }
}