package fr.uge.plutus.layout.transaction_list


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import fr.uge.plutus.navigation.NavigationRoute
import fr.uge.plutus.ui.ant.Ant
import fr.uge.plutus.ui.components.*

@Composable
fun TransactionListScreen(
    navController: NavHostController,
) {
    val viewModel = hiltViewModel<TransactionListViewModel>()
    val state = viewModel.state.value
    //val formater = getDateInstance()//SimpleDateFormat("dd/MM/yyyy")

    val sheetVisible = remember { mutableStateOf(false) }

    AntBottomSheetScaffold(
        topBar = {
            AntTopBar(
                title = "Transactions",
                leadingIcons = listOf {
                    AntActionButton(
                        icon = Icons.Outlined.ArrowBack,
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
                            sheetVisible.value = !sheetVisible.value
                        }
                    )
                }
            )
        },
        sheetPeekHeight = 0.dp,
        sheetVisible = sheetVisible,
        sheetContent = {
            Column(
                modifier = Modifier
                    .padding(Ant.spacing.default)
                    .background(
                        color = Ant.colors.gray_1,
                        shape = Ant.shapes.default
                    ),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ){
                AntActionCard(
                    label = "Credit",
                    desc = "Create new income",
                    icon = Icons.Outlined.TrendingUp,
                    onClick = {
                        navController.navigate(NavigationRoute.NewTransaction.route)
                    }
                )
                AntActionCard(
                    label = "Debit",
                    desc = "Create new expense",
                    icon = Icons.Outlined.TrendingDown,
                    onClick = {}
                )
                AntActionCard(
                    label = "Transfer",
                    desc = "Create new transfer",
                    icon = Icons.Outlined.SyncAlt,
                    onClick = {}
                )
            }
        }
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            AntTextField()
            AntPagerLayout(
                pages = listOf (
                    AntPagerLayoutItem(
                        label = "Past",
                        content = {
                            LazyColumn(
                                modifier = Modifier.padding(horizontal = Ant.spacing.default),
                                verticalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                items(state.transactions) {item ->
                                    AntCard(
                                        title = item.desc,
                                        description = item.timestamp.toString(),
                                        extras = "${item.price}"
                                    )
                                }
                            }
                        }
                    ),
                    AntPagerLayoutItem(
                        label = "Coming",
                        content = {
                            Text("This feature is in progress...")
                        }
                    )
                )
            )
        }
    }
}
