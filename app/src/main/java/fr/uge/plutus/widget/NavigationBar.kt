package fr.uge.plutus.widget

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import fr.uge.plutus.ui.components.AntBottomBar
import fr.uge.plutus.ui.components.AntBottomBarItem

sealed class NavigationBarItem (
    route: String,
    title: String,
    icon: ImageVector,
    icon_focus: ImageVector
) : AntBottomBarItem(route, title, icon, icon_focus) {

    object Home: NavigationBarItem (
        route = "home",
        title = "Home",
        icon = Icons.Outlined.Home,
        icon_focus = Icons.Filled.Home
    )

    object History: NavigationBarItem (
        route = "history",
        title = "Transactions",
        icon = Icons.Outlined.AccessTime,
        icon_focus = Icons.Filled.Timelapse
    )

    object Wallets: NavigationBarItem (
        route = "wallets",
        title = "Wallets",
        icon = Icons.Outlined.AccountBalanceWallet,
        icon_focus = Icons.Filled.AccountBalanceWallet
    )

    object Settings: NavigationBarItem (
        route = "settings",
        title = "Settings",
        icon = Icons.Outlined.Settings,
        icon_focus = Icons.Filled.Settings
    )

}

@Composable
fun NavigationBar(
    navController: NavHostController
) {
    val items = listOf(
        NavigationBarItem.Home,
        NavigationBarItem.History,
        NavigationBarItem.Wallets,
        NavigationBarItem.Settings
    )

    AntBottomBar(
        navController = navController,
        items = items
    )

}