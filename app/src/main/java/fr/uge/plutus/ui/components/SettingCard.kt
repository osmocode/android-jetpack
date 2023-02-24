package fr.uge.plutus.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import fr.uge.plutus.ui.ant.Ant

@Composable
fun SettingCard(
    label: String,
    desc: String = "",
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(
                horizontal = Ant.spacing.default,
                vertical = Ant.spacing.small
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
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


@Preview
@Composable
fun SettingCardPreview(

) {
    SettingCard(
        label = "Dark mode",
        desc = "By default use system mode",
        onClick = { }
    )
}