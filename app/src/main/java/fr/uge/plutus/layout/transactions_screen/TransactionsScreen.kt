package fr.uge.plutus.layout.transactions_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import fr.uge.plutus.data.model.Transaction
import fr.uge.plutus.navigation.NavigationRoute
import fr.uge.plutus.ui.ant.Ant
import fr.uge.plutus.ui.components.*
import fr.uge.plutus.ui.field.AntTextField
import java.text.DateFormat
import java.util.*

@Composable
fun TransactionsScreen(
    navController: NavHostController,
    sheetVisible: MutableState<Boolean> = remember { mutableStateOf(false) },
    viewModel: TransactionsViewModel = hiltViewModel()
) {

    val search = remember { mutableStateOf("") }

    AntBottomSheetScaffold(
        sheetPeekHeight = 0.dp,
        sheetVisible = sheetVisible,
        sheetContent = {
            Column(
                modifier = Modifier
                    .padding(Ant.spacing.default)
                    .clip(Ant.shapes.default)
                    .background(color = Ant.colors.gray_1),
                verticalArrangement = Arrangement.spacedBy(Ant.spacing.small)
            ) {
                AntActionCard(
                    icon = transactionTypeIcon(type = "CREDIT"),
                    label = "Credit",
                    desc = "Create new income",
                    onClick = {
                        navController.navigate(
                            route = NavigationRoute.MainScreen.TransactionScreen.createTransaction(
                                Transaction.Type.CREDIT
                            )
                        )
                    }
                )
                AntActionCard(
                    icon = transactionTypeIcon(type = "DEBIT"),
                    label = "Debit",
                    desc = "Create new expense",
                    onClick = {
                        navController.navigate(
                            route = NavigationRoute.MainScreen.TransactionScreen.createTransaction(
                                Transaction.Type.DEBIT
                            )
                        )
                    }
                )
                AntActionCard(
                    icon = transactionTypeIcon(type = "TRANSFER"),
                    label = "Transfer",
                    desc = "Create new transfer",
                    onClick = {
                        navController.navigate(
                            route = NavigationRoute.MainScreen.TransactionScreen.createTransaction(
                                Transaction.Type.TRANSFER
                            )
                        )
                    }
                )
            }
        },
        topBar = {
            AntTopBar(
                title = "Transactions",
                sheetVisible = sheetVisible,
                leadingIcons = listOf {
                    AntActionButton(
                        icon = Icons.Outlined.ArrowBack,
                        onClick = { navController.popBackStack() }
                    )
                },
                trailingIcons = listOf {
                    AntActionButton(
                        type = AntActionButtonType.PRIMARY,
                        icon = Icons.Outlined.Add,
                        title = "New Transaction",
                        onClick = { sheetVisible.value = !sheetVisible.value }
                    )
                }
            )
        }
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(Ant.spacing.default)
        ) {
            AntTextField(
                modifier = Modifier.padding(horizontal = Ant.spacing.default),
                leadingIcon = Icons.Outlined.Search,
                text = search
            )
            AntPagerLayout(
                pages = listOf(
                    AntPagerLayoutItem(
                        label = "Past",
                        content = {
                            TransactionScreenContent(
                                navController = navController,
                                transactions = viewModel.state.value.past,
                                viewModel = viewModel
                            )
                        }
                    ),
                    AntPagerLayoutItem(
                        label = "Coming",
                        content = {
                            TransactionScreenContent(
                                navController = navController,
                                transactions = viewModel.state.value.coming,
                                viewModel = viewModel
                            )
                        }
                    ),
                )
            )
        }
    }
}

@Composable
fun TransactionScreenContent(
    navController: NavHostController,
    transactions: List<Transaction>?,
    viewModel: TransactionsViewModel
) {
    if (transactions == null) return
    val formatter = DateFormat.getDateInstance()
    LazyColumn(
        modifier = Modifier.padding(horizontal = Ant.spacing.default),
    ) {
        items(transactions) { transaction ->
            AntCard(
                title = transaction.title,
                description = formatter.format(Date(transaction.timestamp.toLong())),
                extras = "${transaction.price}",
                icon = transactionTypeIcon(type = transaction.type),
                leadingIcon = listOf(
                    AntCardActionItem(
                        icon = Icons.Outlined.Delete,
                        color = Ant.colors.primary_text,
                        onClick = {
                            viewModel.onEvent(TransactionsEvent.TransactionsDelete(transaction.transactionId!!))
                        },
                    )
                ),
                trailingIcon = listOf(
                    AntCardActionItem(
                        icon = Icons.Outlined.Edit,
                        color = Ant.colors.primary_text,
                        onClick = {
                            navController.navigate(
                                route = NavigationRoute.MainScreen.TransactionScreen.updateTransaction(
                                    transaction = transaction
                                )
                            )
                        },
                    ),
                    AntCardActionItem(
                        icon = Icons.Outlined.FileCopy,
                        color = Ant.colors.primary_text,
                        onClick = {
                            navController.navigate(
                                route = NavigationRoute.MainScreen.TransactionScreen.duplicateTransaction(
                                    transaction = transaction
                                )
                            )
                        },
                    )
                )
            )
        }
    }
}

fun transactionTypeIcon(
    type: String
): ImageVector {
    return when (type) {
        "CREDIT" -> Icons.Outlined.TrendingUp
        "DEBIT" -> Icons.Outlined.TrendingDown
        "TRANSFER" -> Icons.Outlined.SyncAlt
        else -> Icons.Outlined.Paid
    }
}