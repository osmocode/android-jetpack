package fr.uge.plutus.layout.transaction_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.SyncAlt
import androidx.compose.material.icons.outlined.TrendingDown
import androidx.compose.material.icons.outlined.TrendingUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import fr.uge.plutus.ui.components.*
import kotlinx.coroutines.launch
import java.text.DateFormat.getDateInstance
import java.util.*

@OptIn(ExperimentalMaterialApi::class)
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

    val coroutineScope = rememberCoroutineScope()
    AntBottomSheetScaffold(
        sheetContent = listOf(
            AntBottomSheetItemState(
                label = "Credit",
                icon = Icons.Outlined.TrendingUp,
                onClick = {}
            ),
            AntBottomSheetItemState(
                label = "Debit",
                icon = Icons.Outlined.TrendingDown,
                onClick = {}
            ),
            AntBottomSheetItemState(
                label = "Transfer",
                icon = Icons.Outlined.SyncAlt,
                onClick = {}
            )
        )
    ) { state ->
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
                    onClick = {
                        coroutineScope.launch {
                            if (state.bottomSheetState.isCollapsed) {
                                state.bottomSheetState.expand()
                            } else {
                                state.bottomSheetState.collapse()
                            }
                        }
                    }
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