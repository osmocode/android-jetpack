package fr.uge.plutus.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.uge.plutus.data.model.Price
import fr.uge.plutus.ui.ant.Ant

@Composable
fun AntAmountInput(
    amount: Price,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth()
            .clip(shape = Ant.shapes.default)
            .background(color = Ant.colors.gray_1,)
            .padding(horizontal = Ant.spacing.default)
            .clickable(onClick = onClick),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            text = "Enter amount",
            color = Ant.colors.secondary_text
        )
        Text(
            text = "${amount}",
            fontWeight = FontWeight.SemiBold,
            color = Ant.colors.primary_text
        )
    }
}

@Preview
@Composable
fun AntAmountInputPreview(

) {
    val amount: Price = Price(
        currency = "$",
        amount = 135.0
    )
    AntAmountInput(
        amount = amount,
        onClick = { }
    )
}