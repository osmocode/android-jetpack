package fr.uge.plutus.layout.transaction_screen.layout

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import fr.uge.plutus.data.model.Tag
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

    val selected = remember { mutableStateListOf<Tag>() }
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
                    TagCard(
                        tag = tags[index],
                        selected = selected.contains(tags[index]),
                        onClick = {
                            if (selected.contains(tags[index]))
                                selected.remove(tags[index])
                            else
                                selected.add(tags[index])
                        }
                    )
                }
            }
            CustomButton(
                title = "Submit",
                onClick = { }
            )
        }
    }
}

@Composable
fun TagCard(
    tag: Tag,
    selected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .height(100.dp)
            .padding(Ant.spacing.small)
            .clip(Ant.shapes.default)
            .background(color = Ant.colors.gray_1)
            .border(
                width = 2.dp,
                color = if (selected) Ant.colors.primary_color_5 else Ant.colors.gray_1,
                shape = Ant.shapes.default
            )
            .clickable(onClick = onClick)
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = tag.label,
            fontSize = 18.sp
        )
    }
}
