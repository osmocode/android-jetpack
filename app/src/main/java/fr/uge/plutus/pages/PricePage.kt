package fr.uge.plutus.pages

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import fr.uge.plutus.data.model.Price
import fr.uge.plutus.ui.components.*
import fr.uge.plutus.ui.field.AntAmountField

@Composable
fun PricePage(
    navController: NavHostController,
    price : Price,
    onSubmit : (Price) -> Unit
) {
    val sheetVisible = remember { mutableStateOf(false) }
    val currency = remember { mutableStateOf(price.currency) }
    val amount = remember { mutableStateOf(price.amount) }
    val buttonDisable = remember { mutableStateOf(true) }
    val focusManger = LocalFocusManager.current

    AntBottomSheetScaffold(
        topBar = {
            AntTopBar(
                title = "Price",
                leadingIcons = listOf {
                    AntActionButton(
                        icon = Icons.Outlined.ArrowBack,
                        onClick = {
                            navController.popBackStack()
                        }
                    )
                }
            )
        },
        sheetPeekHeight = 0.dp,
        sheetVisible = sheetVisible,
        sheetContent = {}
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .pointerInput(Unit){
                    detectTapGestures(onTap = {
                        focusManger.clearFocus()
                    })
                },
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CustomButton(
                    title = "Currency",
                    type = CustomButtonType.LINK,
                    leadingIcon = Icons.Outlined.ChevronRight,
                    onClick = {}
                )
                AntAmountField(
                    prefix = currency,
                    amount = amount
                )
            }
            CustomButton(
                title = "Submit",
                type = CustomButtonType.PRIMARY,
                disabled = buttonDisable,
                onClick = {
                    onSubmit(Price(
                        currency = currency.value,
                        amount = amount.value
                    ))
                    focusManger.clearFocus()
                    navController.popBackStack()
                }
            )
        }
    }

    LaunchedEffect(key1 = amount.value){
        buttonDisable.value = price.amount == amount.value
    }
}


@Preview
@Composable
fun PricePagePreview(

) {
    val navController = rememberNavController()

    PricePage(
        navController = navController,
        price = Price("$", 0.0)
    ){}
}