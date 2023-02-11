package fr.uge.plutus.layout.transaction_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import fr.uge.plutus.ui.components.AntCard
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun TransactionListScreen(
    navController: NavHostController
) {
    val context = LocalContext.current.applicationContext
    val viewModel =
        viewModel<TransactionListViewModel>(factory = TransactionListViewModelFactory(context))
    val state = viewModel.state.value
    val formater = SimpleDateFormat("dd/MM/yyyy")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(modifier = Modifier.fillMaxSize()) {

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
}