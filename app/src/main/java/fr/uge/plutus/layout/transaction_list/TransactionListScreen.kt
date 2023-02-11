package fr.uge.plutus.layout.transaction_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import fr.uge.plutus.ui.components.AntCard
import fr.uge.plutus.ui.components.AntPagerLayout
import fr.uge.plutus.ui.components.AntTabBarItem
import fr.uge.plutus.ui.components.AntTextField
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun TransactionListScreen(
    navController: NavHostController
) {
    val context = LocalContext.current.applicationContext
    val viewModel = viewModel<TransactionListViewModel>(
        factory = TransactionListViewModelFactory(context)
    )
    val state = viewModel.state.value
    val formater = SimpleDateFormat("dd/MM/yyyy")

    val items = listOf(
        AntTabBarItem(
            label = "Past",
            content = {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            horizontal = 15.dp
                        )
                ) {

                    items(state.transactions) {
                        Column(modifier = Modifier.fillMaxSize()) {
                            AntCard(
                                title = it.desc,
                                description = formater.format(Date(it.timestamp.toLong())),
                                extras = "${it.price.amount} ${it.price.currency}",
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        ),
        AntTabBarItem(
            label = "Coming",
            content =  { page ->
                Box {
                    Text(
                        text = "Page: $page",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = "History",
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            modifier = Modifier.padding(15.dp)
        )
        AntTextField()
        AntPagerLayout(
            navController = navController,
            pages = items
        )
    }
}