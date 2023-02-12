package fr.uge.plutus.layout.transaction_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import fr.uge.plutus.ui.components.*
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

    AntTopBar(
        title = "Transaction",
        backIcon = Icons.Default.ArrowBack,
        backOnClick = {
          navController.popBackStack()
        },
        trailingIcon = {
            AntActionButton(
                modifier = Modifier.padding(end = 16.dp),
                type = AntActionButtonType.PRIMARY,
                icon = Icons.Outlined.Add,
                title = "New Transaction",
                onClick = { /*TODO*/ }
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            AntTextField()
            AntPagerLayout(
                navController = navController,
                pages = items
            )
        }
    }
}

@Preview
@Composable
fun TransactionListScreenPreview(

) {
    val navController = rememberNavController()
    TransactionListScreen(
        navController = navController
    )
}