package fr.uge.plutus.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fr.uge.plutus.ui.components.CustomButton
import fr.uge.plutus.ui.components.CustomButtonType


@Composable
fun TransactionWidget(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color.LightGray,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(15.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = "Widget"
        )
        CustomButton(
            title = "See all",
            type = CustomButtonType.PRIMARY,
            trailingIcon = Icons.Outlined.ChevronRight,
            onClick = {
                navController.navigate(NavigationRoute.Transaction.path)
            }
        )
    }

}
