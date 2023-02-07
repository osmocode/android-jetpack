package fr.uge.plutus.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import fr.uge.plutus.ui.components.AntCard
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
            LazyColumn{
                items(10){
                    AntCard(title = "Crous", description = "07 fév 2023", extras = "3,25€")
                }
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