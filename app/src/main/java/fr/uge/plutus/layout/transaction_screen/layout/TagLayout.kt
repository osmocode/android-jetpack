package fr.uge.plutus.layout.transaction_screen.layout

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import fr.uge.plutus.layout.transaction_screen.TransactionEvent
import fr.uge.plutus.layout.transaction_screen.TransactionViewModel
import fr.uge.plutus.ui.ant.Ant
import fr.uge.plutus.ui.components.*
import fr.uge.plutus.ui.field.AntTextField

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TagLayout(
    navController: NavHostController,
    sheetVisible: MutableState<Boolean> = remember { mutableStateOf(false) },
    viewModel: TransactionViewModel
) {

    val disabled = remember { mutableStateOf(true) }
    val tag = remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    LaunchedEffect(sheetVisible.value) {
        if (!sheetVisible.value) {
            focusManager.clearFocus()
        }
    }

    LaunchedEffect(tag.value) {
        disabled.value = tag.value.isEmpty() || tag.value.isBlank()
    }

    AntBottomSheetScaffold(
        sheetPeekHeight = 0.dp,
        sheetVisible = sheetVisible,
        sheetContent = {
            Column(
                modifier = Modifier.padding(Ant.spacing.default),
                verticalArrangement = Arrangement.spacedBy(Ant.spacing.default)
            ) {
                AntTextField(
                    text = tag,
                    placeHolder = "Tag name",
                )
                CustomButton(
                    title = "Create tag",
                    type = CustomButtonType.PRIMARY,
                    disabled = disabled,
                    onClick = {
                        viewModel.onEvent(TransactionEvent.TransactionTagCreate(tag.value))
                        sheetVisible.value = false
                    }
                )
            }
        },
        topBar = {
            AntTopBar(
                sheetVisible = sheetVisible,
                title = "Choice tags",
                leadingIcons = listOf {
                    AntActionButton(
                        icon = Icons.Outlined.ArrowBack,
                        onClick = { navController.popBackStack() }
                    )
                },
                trailingIcons = listOf {
                    AntActionButton(
                        icon = Icons.Outlined.Add,
                        type = AntActionButtonType.PRIMARY,
                        title = "Tag",
                        onClick = { sheetVisible.value = !sheetVisible.value }
                    )
                }
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize(),
                cells = GridCells.Fixed(2),
                contentPadding = PaddingValues(Ant.spacing.small)
            ) {
                val tags = viewModel.state.value.tags
                items(tags.size) { index ->
                    Box(
                        modifier = Modifier
                            .height(100.dp)
                            .padding(Ant.spacing.small)
                            .clip(Ant.shapes.default)
                            .background(color = Ant.colors.gray_1)
                            .clickable { }
                    ) {
                        Text(
                            modifier = Modifier.align(Alignment.Center),
                            text = tags[index].label,
                            fontSize = 18.sp
                        )
                    }
                }
            }
            CustomButton(
                title = "Submit",
                onClick = { }
            )
        }
    }
}

