package fr.uge.plutus.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import fr.uge.plutus.SplashScreen
import fr.uge.plutus.layout.wallet_screen.WalletScreen
import fr.uge.plutus.storage.StorageProvider
import fr.uge.plutus.storage.StorageViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavigationRoot(
    navController: NavHostController,
    storageViewModel: StorageViewModel = hiltViewModel()
) {
    StorageProvider {
        AnimatedNavHost(
            navController = navController,
            enterTransition = enterTransition(),
            exitTransition = exitTransition(),
            popEnterTransition = popEnterTransition(),
            popExitTransition = popExitTransition(),
            startDestination = NavigationRoute.SplashScreen.route
        ) {
            /* SPLASH SCREEN */
            composable(
                route = NavigationRoute.SplashScreen.route,
                content = {
                    val ready = remember { mutableStateOf(false) }
                    val current = remember { mutableStateOf(-1) }
                    LaunchedEffect(storageViewModel.ready.value) {
                        if (storageViewModel.ready.value) {
                            current.value =
                                if (storageViewModel.state.value.wallet == null) -1 else storageViewModel.state.value.wallet!!
                            ready.value = true
                        }
                    }
                    SplashScreen(
                        ready = ready,
                        onFinish = {
                            navController.popBackStack()
                            navController.navigate(
                                route = if (current.value == -1) NavigationRoute.WalletScreen.route else NavigationRoute.MainScreen.route
                            )
                        }
                    )
                }
            )
            /* SET WALLET BEFORE START */
            composable(
                route = NavigationRoute.WalletScreen.route,
                content = {
                    LaunchedEffect(storageViewModel.state.value.wallet) {
                        val w = storageViewModel.state.value.wallet
                        if (w != null && w != -1) {
                            navController.popBackStack()
                            navController.navigate(route = NavigationRoute.MainScreen.route)
                        }
                    }
                    WalletScreen()
                }
            )
            /* MAIN NAVIGATION */
            composable(
                route = NavigationRoute.MainScreen.route,
                content = {
                    NavigationMain(
                        navHostController = navController
                    )
                }
            )
        }
    }
}