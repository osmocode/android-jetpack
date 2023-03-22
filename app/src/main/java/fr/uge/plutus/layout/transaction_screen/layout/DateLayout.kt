package fr.uge.plutus.layout.transaction_screen.layout

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
import fr.uge.plutus.layout.transaction_screen.TransactionEvent
import fr.uge.plutus.layout.transaction_screen.TransactionViewModel
import fr.uge.plutus.ui.ant.Ant
import fr.uge.plutus.ui.components.*
import fr.uge.plutus.ui.field.AntDateField
import java.util.*

@Composable
fun DateLayout(
    navController: NavHostController,
    sheetVisible: MutableState<Boolean> = remember { mutableStateOf(false) },
    viewModel: TransactionViewModel
) {
    val date = remember {
        val d = Calendar.getInstance()
        viewModel.state.value.transactionWithTags.transaction.timestamp.takeIf { it != 0L }?.let {
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
                title = "Enter date",
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
                        TransactionEvent.TransactionUpdateDate(
                            date.value.timeInMillis
                        )
                    )
                    navController.popBackStack()
                }
            )
        }
    }
}