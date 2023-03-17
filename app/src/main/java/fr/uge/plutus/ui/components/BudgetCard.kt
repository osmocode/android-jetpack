package fr.uge.plutus.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import fr.uge.plutus.ui.ant.Ant

@Composable
fun AntBudgetCard(
    label: String,
    current: Double,
    target: Double,
    color: Color,
    onClick: () -> Unit
) {

    val percent = if ((current / target) > 1) 1.0f else (current / target).toFloat()

    Box(
        modifier = Modifier
            .clip(shape = Ant.shapes.default)
            .clickable(onClick = onClick)
            .fillMaxWidth()
            .padding(Ant.spacing.default)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(Ant.spacing.small)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = label,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = Ant.colors.primary_text,
                    )
                    Text(
                        text = "$current out of $target",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        color = Ant.colors.secondary_text
                    )
                }
                Text(
                    text = "${percent * 100} %",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = Ant.colors.primary_text
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Ant.spacing.small)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(Ant.shapes.default)
                        .background(color = Ant.colors.gray_5)
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth(percent)
                        .fillMaxHeight()
                        .clip(Ant.shapes.default)
                        .background(color = color)
                        .animateContentSize()
                )
            }
        }
    }
}

@Preview
@Composable
fun AntBudgetCardPreview(

) {
    AntBudgetCard(
        label = "voiture",
        current = 189.0,
        target = 1000.0,
        color = Ant.colors.primary_color_5,
        onClick = {}
    )
}