package fr.uge.plutus.widget

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import fr.uge.plutus.ui.components.CustomButton
import fr.uge.plutus.ui.components.CustomButtonType

@Composable
fun TransactionPriceWidget (
    navController: NavHostController,
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Column {
            TopAppBar(
                title = {
                    Text(
                        text = "Header"
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {

                        }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
            Text(
                text = "Enter Amount",
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                modifier = Modifier.padding(15.dp)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomButton(
                modifier = Modifier.padding(horizontal = 16.dp),
                title = "Currency",
                type = CustomButtonType.LINK,
                trailingIcon = Icons.Outlined.ChevronRight,
                onClick = {
                    //navController.navigate(NavigationRoute.Transaction.path)
                },
            )
            Text(
                text = "$00.00",
                fontSize = 50.sp
            )
            // champs pour faire renter le price
        }
        CustomButton(
            modifier = Modifier.padding(16.dp),
            title = "Enter",
            type = CustomButtonType.DEFAULT,
            trailingIcon = Icons.Outlined.ChevronRight,
            onClick = {

            }
        )
    }

}