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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.SyncAlt
import androidx.compose.material.icons.outlined.TrendingDown
import androidx.compose.material.icons.outlined.TrendingUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.uge.plutus.ui.ant.Ant

data class AntBottomSheetItemState(
    val icon: ImageVector,
    val label: String,
    val desc: String,
    val onClick: () -> Unit
)

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AntBottomSheetScaffold(
    sheetContent: List<AntBottomSheetItemState>,
    sheetVisible: MutableState<Boolean>,
    content: @Composable () -> Unit
) {
    val state = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )

    BottomSheetScaffold(
        scaffoldState = state,
        sheetGesturesEnabled = true,
        sheetPeekHeight = 0.dp,
        sheetShape = RoundedCornerShape(
            topStart = 16.dp,
            topEnd = 16.dp
        ),
        sheetElevation = 0.dp,
        sheetContent = {
            Column(
                modifier = Modifier
                    .background(color = Color.White)
                    .padding(top = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .clip(shape = CircleShape)
                        .width(60.dp)
                        .height(5.dp)
                        .alpha(0.2f)
                        .background(
                            color = Color.Black,
                        )
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .background(
                            color = Ant.colors.input_backgroundColor,
                            shape = RoundedCornerShape(10.dp)
                        )
                ) {
                    Column(
                        modifier = Modifier.padding(vertical = 5.dp),
                        verticalArrangement = Arrangement.spacedBy(5.dp),
                    ) {
                        sheetContent.forEach { item ->
                            AntBottomSheetItem(item)
                        }
                    }
                }
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
            Box{
                content()
                AnimatedVisibility(
                    visible = sheetVisible.value,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .alpha(0.4f)
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

@Composable
fun AntBottomSheetItem(
    item: AntBottomSheetItemState
) {
    Box(
        modifier = Modifier
            .clip(
                shape = RoundedCornerShape(10.dp)
            )
            .clickable(
                onClick = item.onClick
            )
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Box(
                modifier = Modifier
                    .padding(start = 5.dp)
                    .size(40.dp)
                    .background(
                        color = Ant.colors.nav_item_focus,
                        shape = CircleShape
                    )
            ) {
                Icon(
                    modifier = Modifier.align(Alignment.Center),
                    imageVector = item.icon,
                    contentDescription = null,
                    tint = Color.White
                )
            }
            Column {
                Text(
                    text = item.label,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Ant.colors.nav_item_focus,
                )
                Text(
                    text = item.desc,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color = Ant.colors.nav_item_focus,
                )
            }

        }
    }
}

@Preview
@Composable
fun AntBottomSheetScaffoldPreview(

) {
    val visible = remember { mutableStateOf(true) }
    AntBottomSheetScaffold(
        sheetContent = listOf(
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
        ),
        sheetVisible = visible,
        content = {
            Box(modifier = Modifier.fillMaxSize())
        }
    )
}
