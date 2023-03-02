package fr.uge.plutus.layout.transaction_screen.layout

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fr.uge.plutus.layout.transaction_screen.TransactionEvent
import fr.uge.plutus.layout.transaction_screen.TransactionViewModel
import fr.uge.plutus.ui.ant.Ant
import fr.uge.plutus.ui.components.*
import fr.uge.plutus.ui.field.AntDescField
import fr.uge.plutus.ui.field.AntTitleField

@Composable
fun DescLayout(
    navController: NavHostController,
    sheetVisible: MutableState<Boolean> = remember { mutableStateOf(false) },
    viewModel: TransactionViewModel
) {
    val title = remember { mutableStateOf(viewModel.state.value.transaction.title) }
    val desc = remember { mutableStateOf(viewModel.state.value.transaction.description) }
    val disabled = remember { mutableStateOf(false) }

    LaunchedEffect(title.value, desc.value) {
        disabled.value = title.value == viewModel.state.value.transaction.title &&
                desc.value == viewModel.state.value.transaction.description
    }

    AntBottomSheetScaffold(
        sheetPeekHeight = 0.dp,
        sheetVisible = sheetVisible,
        sheetContent = { /*TODO*/ },
        topBar = {
            AntTopBar(
                title = "Enter a description",
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
            Column(verticalArrangement = Arrangement.spacedBy(Ant.spacing.default)) {
                AntTitleField(
                    title = title,
                    placeholder = "Title"
                )
                AntDescField(
                    desc = desc,
                    placeholder = "Enter a description"
                )
            }
            CustomButton(
                disabled = disabled,
                title = "Submit",
                type = CustomButtonType.PRIMARY,
                onClick = {
                    viewModel.onEvent(
                        TransactionEvent.TransactionUpdateDesc(
                            title = title.value,
                            desc = desc.value
                        )
                    )
                    navController.popBackStack()
                }
            )
        }
    }
}