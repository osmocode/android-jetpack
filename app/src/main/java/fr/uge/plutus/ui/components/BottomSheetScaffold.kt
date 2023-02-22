package fr.uge.plutus.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import fr.uge.plutus.ui.ant.Ant


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AntBottomSheetScaffold(
    sheetPeekHeight: Dp,
    sheetVisible: MutableState<Boolean>,
    sheetContent: @Composable () -> Unit,
    topBar: @Composable () -> Unit,
    content: @Composable () -> Unit
) {
    val state = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )

    BottomSheetScaffold(
        backgroundColor = Color.Transparent,
        topBar = topBar,
        scaffoldState = state,
        sheetGesturesEnabled = true,
        sheetPeekHeight = sheetPeekHeight,
        sheetShape = RoundedCornerShape(
            topStart = 16.dp,
            topEnd = 16.dp
        ),
        sheetElevation = 0.dp,
        sheetContent = {
            Column(
                modifier = Modifier
                    .background(color = Ant.colors.background)
                    .padding(top = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .clip(shape = CircleShape)
                        .width(60.dp)
                        .height(5.dp)
                        .alpha(0.2f)
                        .background(color = Ant.colors.primary_text)
                )
                sheetContent.invoke()
            }
        },
        content = {
            LaunchedEffect(
                key1 = sheetVisible.value,
            ) {
                if (sheetVisible.value)
                    state.bottomSheetState.expand()
                else
                    state.bottomSheetState.collapse()
            }
            LaunchedEffect(
                key1 = state.bottomSheetState.currentValue
            ) {
                if (state.bottomSheetState.isCollapsed)
                    sheetVisible.value = false
            }
            Box {
                content.invoke()
                AnimatedVisibility(
                    visible = sheetVisible.value,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .alpha(0.6f)
                            .background(color = Color.Black)
                            .clickable {
                                if (sheetVisible.value)
                                    sheetVisible.value = false
                            }
                    )
                }
            }
        }
    )
}

@Preview
@Composable
fun AntBottomSheetScaffoldPreview(

) {
    /*
    AntBottomSheetItemState(
        label = "Credit",
        desc = "Credit",
        icon = Icons.Outlined.TrendingUp,
        onClick = {}
    ),
    AntBottomSheetItemState(
        label = "Debit",
        desc = "Debit",
        icon = Icons.Outlined.TrendingDown,
        onClick = {}
    ),
    AntBottomSheetItemState(
        label = "Transfer",
        desc = "Transfer",
        icon = Icons.Outlined.SyncAlt,
        onClick = {}
    )
    */
}
