package fr.uge.plutus.pages


import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fr.uge.plutus.ui.ant.Ant
import fr.uge.plutus.ui.components.*
import fr.uge.plutus.widget.BudgetWidget
import fr.uge.plutus.widget.TransactionWidget

@Composable
fun HomePage(
    navController: NavHostController
) {
    val sheetVisible = remember { mutableStateOf(false) }

    AntBottomSheetScaffold(
        topBar = {
            AntTopBar(
                trailingIcons = listOf {
                    AntActionButton(
                        icon = Icons.Outlined.Notifications,
                        onClick = {
                        }
                    )
                }
            )
        },
        sheetPeekHeight = 0.dp,
        sheetVisible = sheetVisible,
        sheetContent = {}
    ) {
        AntScrollablePagerLayout(
            pages = listOf (
                ScrollablePagerLayoutItem(
                    label = "Home",
                    content = {
                        Column(
                            modifier = Modifier.fillMaxSize()
                        ){
                            Spacer(modifier = Modifier.size(10.dp))
                            TransactionWidget(
                                navController = navController,
                                modifier = Modifier.padding(horizontal = Ant.spacing.default)
                            )
                        }
                    }
                ),
                ScrollablePagerLayoutItem(
                    label = "Incoming",
                    content = {
                        Column(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Spacer(modifier = Modifier.size(10.dp))
                            BudgetWidget(
                                title = "Incoming",
                                data = listOf(
                                    DonutChartItem(
                                        label = "Gift",
                                        value = 1450.99
                                    ),
                                    DonutChartItem(
                                        label = "Salary",
                                        value = 45.99
                                    ),
                                    DonutChartItem(
                                        label = "Interest Money",
                                        value = 99.50
                                    ),
                                    DonutChartItem(
                                        label = "Demonstration",
                                        value = 249.00
                                    )
                                )
                            )
                        }
                    }
                ),
                ScrollablePagerLayoutItem(
                    label = "Expends",
                    content = {
                        Box(modifier = Modifier.fillMaxSize()) {
                            Text(text = "Actions", modifier = Modifier.align(Alignment.Center))
                        }
                    }
                ),
                ScrollablePagerLayoutItem(
                    label = "Transfers",
                    content = {
                        Box(modifier = Modifier.fillMaxSize()) {
                            Text(text = "Statistics", modifier = Modifier.align(Alignment.Center))
                        }
                    }
                )
            )
        )
    }
}
