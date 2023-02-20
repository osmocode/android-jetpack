package fr.uge.plutus.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import fr.uge.plutus.ui.components.*
import fr.uge.plutus.widget.TransactionWidget


@Composable
fun HomeScreen(
    navController: NavHostController
) {
   /* Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = "Home",
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            modifier = Modifier.padding(15.dp)
        )
        TransactionWidget(
            navController = navController,
            modifier = Modifier.padding(horizontal = 15.dp)
        )
    }*/

    val pages = listOf(
        ScrollablePagerLayoutItem(
            label = "Home",
            content = {
                Box(modifier = Modifier.fillMaxSize()){
                    TransactionWidget(
                        navController = navController,
                        modifier = Modifier.padding(horizontal = 15.dp)
                    )
                }
            }
        ),
        ScrollablePagerLayoutItem(
            label = "Budget",
            content = {
                Box(modifier = Modifier.fillMaxSize()){
                    Text(text = "Budget", modifier = Modifier.align(Alignment.Center))
                }
            }
        ),
        ScrollablePagerLayoutItem(
            label = "Actions",
            content = {
                Box(modifier = Modifier.fillMaxSize()){
                    Text(text = "Actions", modifier = Modifier.align(Alignment.Center))
                }
            }
        ),
        ScrollablePagerLayoutItem(
            label = "Statistics",
            content = {
                Box(modifier = Modifier.fillMaxSize()){
                    Text(text = "Statistics", modifier = Modifier.align(Alignment.Center))
                }
            }
        ),
        ScrollablePagerLayoutItem(
            label = "New Feature",
            content = {
                Box(modifier = Modifier.fillMaxSize()){
                    Text(text = "New Feature in dev", modifier = Modifier.align(Alignment.Center))
                }
            }
        )
    )

    AntTopBar(
        backOnClick = {},
        trailingIcon = {
            AntActionButton(
                modifier = Modifier.padding(end = 16.dp),
                type = AntActionButtonType.LINK,
                icon = Icons.Outlined.Notifications,
                title = "",
                onClick = { /*TODO*/ })
        }
    ) {
        ScrollablePagerLayout(
            navController = navController,
            pages = pages
        )
    }
}


@Preview
@Composable
fun HomeScreenPreview(){
    val navController = rememberNavController()
    HomeScreen(navController = navController)
}