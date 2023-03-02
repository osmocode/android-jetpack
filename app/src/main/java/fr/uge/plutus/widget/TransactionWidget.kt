package fr.uge.plutus.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.TrendingDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import fr.uge.plutus.data.model.Transaction
import fr.uge.plutus.navigation.NavigationRoute
import fr.uge.plutus.storage.SettingsWallet
import fr.uge.plutus.ui.ant.Ant
import fr.uge.plutus.ui.components.CustomButton
import fr.uge.plutus.ui.components.CustomButtonType

@Composable
fun TransactionWidget(
    navController: NavHostController,
    transaction: List<Transaction>?,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(Ant.shapes.default)
            .background(color = Ant.colors.gray_1)
            .padding(Ant.spacing.default),
        verticalArrangement = Arrangement.spacedBy(Ant.spacing.default)
    ) {
        Text(
            text = "Last transactions",
            color = Ant.colors.primary_text,
            fontWeight = FontWeight.Medium
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(Ant.spacing.small)
        ) {
            if (transaction == null) {

            } else if (transaction.isEmpty()) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "You have any transactions...",
                    color = Ant.colors.secondary_text,
                    textAlign = TextAlign.Center
                )
            } else {
                transaction.forEach {
                    TransactionWidgetCard(
                        icon = Icons.Outlined.TrendingDown,
                        label = it.title,
                        desc = it.description,
                        onClick = {}
                    )
                }
            }
        }
        val wallet = SettingsWallet.current
        CustomButton(
            title = "See all",
            type = CustomButtonType.PRIMARY,
            trailingIcon = Icons.Outlined.ChevronRight,
            onClick = {
                navController.navigate(
                    route = NavigationRoute.MainScreen.TransactionsScreen.route(wallet)
                )
            }
        )
    }
}

@Composable
fun TransactionWidgetCard(
    icon: ImageVector,
    label: String,
    desc: String = "",
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(shape = Ant.shapes.default)
            .clickable(onClick = onClick)
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Ant.spacing.small)
        ) {
            Box(
                modifier = Modifier
                    .padding(Ant.spacing.small)
                    .size(40.dp)
                    .background(
                        color = Ant.colors.primary_color_1,
                        shape = CircleShape
                    )
            ) {
                Icon(
                    modifier = Modifier.align(Alignment.Center),
                    imageVector = icon,
                    contentDescription = null,
                    tint = Ant.colors.gray_6
                )
            }
            Column {
                Text(
                    text = label,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Ant.colors.primary_text,
                )
                Text(
                    text = desc,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color = Ant.colors.secondary_text
                )
            }
        }
    }
}