package fr.uge.plutus.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import fr.uge.plutus.ui.ant.Ant
import fr.uge.plutus.ui.utils.shadow

data class AntNavBarItem(
    val route: String,
    val title: String,
    val icon: ImageVector,
    val icon_focus: ImageVector
) {

}

@Composable
fun AntNavBar(
    items: List<AntNavBarItem>
) {

    val radius: Dp = 15.dp;

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(50.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .shadow(
                    color = Ant.colors.nav_shadow,
                    borderRadius = 5.dp,
                    blurRadius = 10.dp,
                    offsetX = 0.dp,
                    offsetY = 5.dp,
                    spread = (-5).dp
                )
        ) {
            Row(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(radius))
                    .background(
                        color = Ant.colors.nav_background
                    )
                    .padding(
                        horizontal = 30.dp,
                        vertical = 15.dp
                    ),
                horizontalArrangement = Arrangement.spacedBy(30.dp),
                verticalAlignment = Alignment.CenterVertically,
            ){
                items.forEach { item ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Icon(
                            imageVector = item.icon,
                            contentDescription = null,
                            modifier = Modifier.size(30.dp),
                            tint = Ant.colors.nav_item_focus
                        )
                    }
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    widthDp = 412,
    heightDp = 915
)
@Composable
fun AntNavBarPreview() {
    var items = listOf(
        AntNavBarItem(route = "home", title = "Home", icon = Icons.Outlined.Home, icon_focus = Icons.Filled.Home),
        AntNavBarItem(route = "transactions", title = "Transactions", icon = Icons.Outlined.Search, icon_focus = Icons.Filled.Search),
        AntNavBarItem(route = "history", title = "History", icon = Icons.Outlined.DateRange, icon_focus = Icons.Filled.DateRange),
        AntNavBarItem(route = "settings", title = "Settings", icon = Icons.Outlined.Settings, icon_focus = Icons.Filled.Settings)
    )
    Scaffold(
        bottomBar = {
            AntNavBar(
                items = items
            )
        },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        )
    }
}