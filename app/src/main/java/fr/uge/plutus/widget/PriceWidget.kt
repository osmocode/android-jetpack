package fr.uge.plutus.widget

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import fr.uge.plutus.ui.components.AntAmountField
import fr.uge.plutus.ui.components.AntTopBar
import fr.uge.plutus.ui.components.CustomButton
import fr.uge.plutus.ui.components.CustomButtonType

@Composable
fun PriceWidget (
    navController: NavHostController,
) {
    val prefix = remember { mutableStateOf("$") }
    val amount = remember { mutableStateOf(10.0) }

    AntTopBar(
        backIcon = Icons.Default.ArrowBack,
        backOnClick = {
            navController.popBackStack()
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = "Enter Amount",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(16.dp)
            )
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
                    onClick = {},
                )

                AntAmountField(prefix = prefix, amount = amount)
            }
            CustomButton(
                type = CustomButtonType.PRIMARY,
                title = "Valid",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Preview
@Composable
fun PriceWidgetPreview(

) {
    val navController = rememberNavController()
    PriceWidget(
        navController = navController
    )
}