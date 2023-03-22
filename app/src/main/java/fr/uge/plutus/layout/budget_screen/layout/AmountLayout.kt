package fr.uge.plutus.layout.budget_screen.layout

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fr.uge.plutus.data.model.Price
import fr.uge.plutus.layout.budget_screen.BudgetEvent
import fr.uge.plutus.layout.budget_screen.BudgetViewModel
import fr.uge.plutus.ui.ant.Ant
import fr.uge.plutus.ui.components.*
import fr.uge.plutus.ui.field.AntAmountField


@Composable
fun AmountLayout(
    navController: NavHostController,
    sheetVisible: MutableState<Boolean> = remember { mutableStateOf(false) },
    viewModel: BudgetViewModel
) {
    val currency = remember { mutableStateOf(viewModel.state.value.budget.targetPrice.currency) }
    val amount = remember { mutableStateOf(viewModel.state.value.budget.targetPrice.amount) }
    val disabled = remember { mutableStateOf(false) }

    LaunchedEffect(currency.value, amount.value) {
        disabled.value =
            currency.value == viewModel.state.value.budget.targetPrice.currency &&
                    amount.value == viewModel.state.value.budget.targetPrice.amount
    }

    AntBottomSheetScaffold(
        sheetPeekHeight = 0.dp,
        sheetVisible = sheetVisible,
        sheetContent = { /*TODO*/ },
        topBar = {
            AntTopBar(
                title = "Enter amount",
                leadingIcons = listOf {
                    AntActionButton(
                        icon = Icons.Outlined.ArrowBack,
                        onClick = { navController.popBackStack() }
                    )
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(Ant.spacing.default),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box {}
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CustomButton(
                    title = "Currency",
                    type = CustomButtonType.LINK,
                    onClick = { }
                )
                AntAmountField(
                    disabled = disabled,
                    prefix = currency,
                    amount = amount
                )
            }
            CustomButton(
                title = "Submit",
                type = CustomButtonType.PRIMARY,
                disabled = disabled,
                onClick = {
                    viewModel.onEvent(
                        BudgetEvent.BudgetUpdatePrice(
                            Price(
                                currency = currency.value,
                                amount = amount.value
                            )
                        )
                    )
                    navController.popBackStack()
                }
            )
        }
    }

}