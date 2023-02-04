package fr.uge.plutus.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import fr.uge.plutus.ui.components.AntPagerLayout
import fr.uge.plutus.ui.components.AntTabBarItem

sealed class TransactionPagerItem(
    label: String,
    content: @Composable (page: Int) -> Unit
): AntTabBarItem(
    label,
    content
) {
    object Past: TransactionPagerItem(
        label = "Past",
        content = { page ->
            Box {
                Text(
                    text = "Page: $page",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    )

    object Coming: TransactionPagerItem(
        label = "Coming",
        content = { page ->
            Box {
                Text(
                    text = "Page: $page",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    )
}

@Composable
fun TransactionPager(
    navController: NavHostController
) {
    val items = listOf(
        TransactionPagerItem.Past,
        TransactionPagerItem.Coming
    )

    AntPagerLayout(
        navController = navController,
        pages = items
    )
}