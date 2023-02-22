package fr.uge.plutus.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.FindInPage
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material.icons.outlined.FindInPage
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import fr.uge.plutus.ui.components.AntBottomBar
import fr.uge.plutus.ui.components.AntBottomBarItem

@Composable
fun NavigationBar(
    navController: NavHostController
) {
    val items = listOf (
        AntBottomBarItem(
            route = "home",
            title = "Home",
            icon = Icons.Outlined.Home,
            icon_focus = Icons.Filled.Home
        ),
        AntBottomBarItem(
            route = "research",
            title = "Research",
            icon = Icons.Outlined.FindInPage,
            icon_focus = Icons.Filled.FindInPage
        ),
        AntBottomBarItem(
            route = "wallets",
            title = "Wallets",
            icon = Icons.Outlined.AccountBalanceWallet,
            icon_focus = Icons.Filled.AccountBalanceWallet
        ),
        AntBottomBarItem(
            route = "settings",
            title = "Settings",
            icon = Icons.Outlined.Settings,
            icon_focus = Icons.Filled.Settings
        )
    )

    val navStackBackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navStackBackEntry?.destination
    val selected = items.any { it.route == currentDestination?.route }

    AnimatedVisibility(
        visible = selected,
        enter = slideInVertically(
            initialOffsetY = { height -> height * 2 / 3  },
            animationSpec = tween(300)
        ),
        exit = slideOutVertically(
            targetOffsetY = { height -> height * 2 / 3 },
            animationSpec = tween(300)
        )
    ) {
        AntBottomBar(
            navController = navController,
            items = items
        )
    }
}