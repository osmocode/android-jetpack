package fr.uge.plutus.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun enterTransition(): (AnimatedContentScope<NavBackStackEntry>.() -> EnterTransition) = {
    slideInHorizontally(
        initialOffsetX = { width -> width },
        animationSpec = tween(300)
    )
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun exitTransition(): (AnimatedContentScope<NavBackStackEntry>.() -> ExitTransition) = {
    slideOutHorizontally(
        targetOffsetX = { width -> -width },
        animationSpec = tween(300)
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun popEnterTransition(): (AnimatedContentScope<NavBackStackEntry>.() -> EnterTransition) = {
    slideInHorizontally(
        initialOffsetX = { width -> -width },
        animationSpec = tween(300)
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun popExitTransition(): (AnimatedContentScope<NavBackStackEntry>.() -> ExitTransition) = {
    slideOutHorizontally(
        targetOffsetX = { width -> width },
        animationSpec = tween(300)
    )
}

fun navEnterTransition(
    initialState: NavBackStackEntry
): EnterTransition? {
    return when (initialState.destination.route) {
        NavigationRoute.MainScreen.HomeScreen.route,
        NavigationRoute.MainScreen.SearchScreen.route,
        NavigationRoute.MainScreen.WalletScreen.route,
        NavigationRoute.MainScreen.SettingsScreen.route ->
            slideInVertically(
                initialOffsetY = { height -> height / 15 },
                animationSpec = tween(300)
            ) + fadeIn(
                animationSpec = tween(150)
            )
        else -> null
    }
}

fun navExitTransition(
    targetState: NavBackStackEntry
): ExitTransition? {
    return when (targetState.destination.route) {
        NavigationRoute.MainScreen.HomeScreen.route,
        NavigationRoute.MainScreen.SearchScreen.route,
        NavigationRoute.MainScreen.WalletScreen.route,
        NavigationRoute.MainScreen.SettingsScreen.route ->
            slideOutVertically(
                targetOffsetY = { height -> height / 15 },
                animationSpec = tween(300)
            ) + fadeOut(
                animationSpec = tween(150)
            )
        else -> null
    }
}
