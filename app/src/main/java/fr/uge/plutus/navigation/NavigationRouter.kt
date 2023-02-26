package fr.uge.plutus.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import fr.uge.plutus.SplashScreen
import fr.uge.plutus.layout.transaction_list.TransactionListScreen
import fr.uge.plutus.layout.transaction_new.TransactionNewScreen
import fr.uge.plutus.layout.wallet_list.WalletListScreen
import fr.uge.plutus.layout.wallet_overview.WalletOverviewScreen
import fr.uge.plutus.pages.*
import fr.uge.plutus.storage.LocalStorage
import fr.uge.plutus.storage.LocalStorageProvider
import fr.uge.plutus.storage.LocalStorageWallet
import fr.uge.plutus.ui.ant.Ant
import fr.uge.plutus.ui.ant.AntTheme


sealed class NavigationRoute(
    val route: String,
) {
    object SplashScreen : NavigationRoute(route = "splash")
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
    object NewWallet: NavigationRoute(route = "newWallet")
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavigationRouter(
    navController: NavHostController,
    localStorage: LocalStorage = hiltViewModel()
) {
    val isLoading = remember { mutableStateOf(true) }
    val bottomSheetVisible = remember { mutableStateOf(false) }
    val wallet = localStorage.getWallet()

    LaunchedEffect(wallet.value) {
        LocalStorageWallet.provides(
            value = if (wallet.value == null) -1 else wallet.value!!
        )
    }

    LaunchedEffect(isLoading.value) {
        if (!isLoading.value) {
            bottomSheetVisible.value = true
        }
    }

    LocalStorageProvider(
        localStorage = localStorage,
        isReady = { ready ->
            isLoading.value = !ready
        },
        content = {
            AntTheme(
                content = {
                    Scaffold(
                        bottomBar = {
                            NavigationBar(
                                isVisible = bottomSheetVisible,
                                navController = navController
                            )
                        }
                    ) {
                        val current = LocalStorageWallet.current
                        LaunchedEffect(isLoading.value, current) {
                            if (!isLoading.value) {
                                navController.popBackStack()
                                navController.navigate(route = if (current == -1) NavigationRoute.NewWallet.route else NavigationRoute.Home.route)
                            }
                        }
                        NavigationRouteHost(
                            navController = navController,
                            startDestination = NavigationRoute.SplashScreen.route
                            //startDestination = NavigationRoute.Home.route
                        ) {
                            composable(
                                route = NavigationRoute.SplashScreen.route,
                                content = {
                                    SplashScreen(
                                        navController = navController,
                                        isLoading = isLoading
                                    )
                                }
                            )
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
                                    WalletOverviewScreen(navController = navController)
                                }
                            )
                            composable(
                                route = NavigationRoute.Settings.route,
                                enterTransition = { navigationRouterEnterAnimation(initialState) },
                                exitTransition = { navigationRouterExitAnimation(targetState) },
                                content = {
                                    SettingsPage(
                                        navBarController = bottomSheetVisible,
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
                                route = NavigationRoute.NewTransaction.route
                                        + "?transactionType={transactionType}"
                                        + "?transactionId={transactionId}",
                                arguments = listOf(
                                    navArgument(
                                        name = "transactionType"
                                    ) {
                                        type = NavType.StringType
                                    },
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
                            composable(
                                route = NavigationRoute.NewWallet.route,
                                content = {
                                    WalletListScreen(
                                        navController = navController
                                    )
                                }
                            )
                        }
                    }
                }
            )
        }
    )
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