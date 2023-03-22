package fr.uge.plutus.layout.budget_screen.layout

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fr.uge.plutus.layout.budget_screen.BudgetEvent
import fr.uge.plutus.layout.budget_screen.BudgetViewModel
import fr.uge.plutus.ui.ant.Ant
import fr.uge.plutus.ui.components.*
import fr.uge.plutus.ui.field.AntDateField
import java.util.*

@Composable
fun DateLayout(
    navController: NavHostController,
    sheetVisible: MutableState<Boolean> = remember { mutableStateOf(false) },
    viewModel: BudgetViewModel,
    start: Boolean,
) {
    val date = remember {
        val d = Calendar.getInstance()
        if (start) viewModel.state.value.budget.dateStart.takeIf { it != 0L }?.let {
            d.timeInMillis = it
        } else viewModel.state.value.budget.dateEnd.takeIf { it != 0L }?.let {
            d.timeInMillis = it
        }
        mutableStateOf(d)
    }
    AntBottomSheetScaffold(
        sheetPeekHeight = 0.dp,
        sheetVisible = sheetVisible,
        sheetContent = { /*TODO*/ },
        topBar = {
            AntTopBar(
                title = "Enter " + (if (start) "start" else "end") + " date",
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
                AntDateField(
                    date = date
                )
            }
            CustomButton(
                title = "Submit",
                type = CustomButtonType.PRIMARY,
                onClick = {
                    viewModel.onEvent(
                        if (start) BudgetEvent.BudgetUpdateDateStart(date.value.timeInMillis)
                        else BudgetEvent.BudgetUpdateDateEnd(date.value.timeInMillis)
                    )
                    navController.popBackStack()
                }
            )
        }
    }
}