package fr.uge.plutus.layout.transaction_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.SyncAlt
import androidx.compose.material.icons.outlined.TrendingDown
import androidx.compose.material.icons.outlined.TrendingUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import fr.uge.plutus.ui.components.*
import java.text.DateFormat.getDateInstance
import java.util.*

@Composable
fun TransactionListScreen(
    navController: NavHostController,
) {
    val viewModel = hiltViewModel<TransactionListViewModel>()
    val state = viewModel.state.value
    val formater = getDateInstance()//SimpleDateFormat("dd/MM/yyyy")

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
                    items(state.transactions){
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

    val visible = remember { mutableStateOf(false) }
    AntBottomSheetScaffold(
        sheetVisible = visible,
        sheetContent = listOf(
            AntBottomSheetItemState(
                label = "Credit",
                desc = "Create new income",
                icon = Icons.Outlined.TrendingUp,
                onClick = {}
            ),
            AntBottomSheetItemState(
                label = "Debit",
                desc = "Create new expense",
                icon = Icons.Outlined.TrendingDown,
                onClick = {}
            ),
            AntBottomSheetItemState(
                label = "Transfer",
                desc = "Create new transfer",
                icon = Icons.Outlined.SyncAlt,
                onClick = {}
            )
        )
    ) {
        AntTopBar(
            title = "Transaction",
            leadingIcons = listOf {
                AntActionButton(
                    type = AntActionButtonType.PRIMARY,
                    icon = Icons.Default.ArrowBack,
                    onClick = {
                        navController.popBackStack()
                    }
                )
            },
            trailingIcons = listOf {
                AntActionButton(
                    type = AntActionButtonType.PRIMARY,
                    icon = Icons.Outlined.Add,
                    title = "New Transaction",
                    onClick = {
                        visible.value = !visible.value
                    }
                )
            }
        )
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

@Composable
fun swipeable(){
    Box(){
    }
}