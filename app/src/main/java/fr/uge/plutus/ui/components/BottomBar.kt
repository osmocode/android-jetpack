package fr.uge.plutus.ui.components

import androidx.compose.animation.*
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FindInPage
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.FindInPage
import androidx.compose.material.icons.outlined.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import fr.uge.plutus.ui.ant.Ant
import fr.uge.plutus.ui.utils.shadow

open class AntBottomBarItem(
    val route: String,
    val title: String,
    val icon: ImageVector,
    val icon_focus: ImageVector
)

@Composable
fun AntBottomBar(
    navController: NavHostController,
    items: List<AntBottomBarItem>
) {
    val navStackBackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navStackBackEntry?.destination

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(50.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .shadow(
                    color = Ant.colors.gray_13,
                    borderRadius = 5.dp,
                    blurRadius = 10.dp,
                    offsetX = 0.dp,
                    offsetY = 5.dp,
                    spread = (-5).dp
                )
        ) {
            Row(
                modifier = Modifier
                    .height(60.dp)
                    .clip(shape = RoundedCornerShape(15.dp))
                    .background(color = Ant.colors.gray_3)
                    .padding(
                        horizontal = 30.dp,
                        //vertical = 15.dp
                    ),
                horizontalArrangement = Arrangement.spacedBy(30.dp),
                verticalAlignment = Alignment.CenterVertically,
            ){
                items.forEach { item ->
                    AntBottomBarItem(
                        item = item,
                        currentDestination = currentDestination,
                        navController = navController
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun RowScope.AntBottomBarItem (
    item: AntBottomBarItem,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    val selected = currentDestination?.hierarchy?.any { it.route == item.route } == true
    val interactionSource = remember { MutableInteractionSource() }

    Box(
        modifier = Modifier
            .clickable(
                indication = null,
                interactionSource = interactionSource
            ){
                if (!selected) {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id)
                        launchSingleTop = true
                    }
                }
            }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val color by animateColorAsState(
                targetValue = if (selected) Ant.colors.primary_color_5 else Ant.colors.primary_color_3
            )
            val size by animateDpAsState(
                targetValue = if (selected) 10.dp else 0.dp
            )
            AnimatedContent(
                targetState = selected,
                transitionSpec = {
                     fadeIn() with fadeOut()
                },
                content = { active ->
                    if (active) {
                        Icon(
                            imageVector = item.icon_focus,
                            contentDescription = item.title,
                            modifier = Modifier.size(30.dp),
                            tint = color
                        )
                    } else {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title,
                            modifier = Modifier.size(30.dp),
                            tint = color
                        )
                    }
                }
            )
            AnimatedVisibility(
                visible = selected,
                enter = slideInVertically() + fadeIn(),
                exit = slideOutVertically() + fadeOut()
            ) {
                Canvas(
                    modifier = Modifier.size(size),
                ) {
                    drawCircle(
                        color = color,
                        radius = 3.dp.toPx()
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun AntBottomBarPreview(

) {
    val navController = rememberNavController()
    val items = listOf(
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
        )
    )


    AntBottomBar(
        navController = navController,
        items = items
    )
}