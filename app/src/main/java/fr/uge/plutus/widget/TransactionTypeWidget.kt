package fr.uge.plutus.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TransactionTypeWidget(
    navController: NavHostController,
    topBar: @Composable (() -> Unit)? = null,
    bottomSheet: @Composable (() -> Unit)? = null
) {
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )
    val coroutineScope = rememberCoroutineScope()
    BottomSheetScaffold(
        topBar = topBar,
        scaffoldState = bottomSheetScaffoldState,
        sheetGesturesEnabled = true,
        sheetPeekHeight = 0.dp,
        sheetShape = RoundedCornerShape(
            topStart = 10.dp,
            topEnd = 10.dp
        ),
        sheetContent = {
            Column {
                Text(
                    text = "Choice 1"
                )
                Text(
                    text = "Choice 1"
                )
                Text(
                    text = "Choice 1"
                )
            }
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Button(
                onClick = {
                    coroutineScope.launch {
                        if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
                            bottomSheetScaffoldState.bottomSheetState.expand()
                        } else {
                            bottomSheetScaffoldState.bottomSheetState.collapse()
                        }
                    }
                }
            ) {

            }
        }
    }
}