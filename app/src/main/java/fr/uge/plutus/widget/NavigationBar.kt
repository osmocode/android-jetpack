package fr.uge.plutus.widget

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
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

@OptIn(ExperimentalAnimationApi::class)
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

    val navStackBackEntry by navController.currentBackStackEntryAsState()
    var currentDestination = navStackBackEntry?.destination
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