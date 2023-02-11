package fr.uge.plutus.widget

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fr.uge.plutus.ui.ant.Ant
import fr.uge.plutus.ui.components.AntCard
import fr.uge.plutus.ui.components.AntPagerLayout
import fr.uge.plutus.ui.components.AntTabBarItem

@Composable
fun TransactionPager(
    navController: NavHostController
) {
    val items = listOf(
        AntTabBarItem (
            label = "Past",
            content = { page ->
                Box(modifier = Modifier
                    .padding(
                        top = 10.dp,
                        start = 10.dp,
                        end = 10.dp))
                LazyColumn{
                    items(10){
                        AntCard(title = "Crous", description = "07 fév 2023", extras = "3,25€")
                    }
                    item { Spacer(modifier = Modifier.size(150.dp)) }
                }
            }
    ),
        AntTabBarItem(
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
    )

    AntPagerLayout(
        navController = navController,
        pages = items
    )
}