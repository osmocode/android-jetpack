package fr.uge.plutus.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.TrendingUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.uge.plutus.ui.ant.Ant

@Composable
fun AntActionCard(
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
            .padding(Ant.spacing.small)
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

@Preview
@Composable
fun AntActionCardPreview(

) {
    AntActionCard(
        icon = Icons.Outlined.TrendingUp,
        label = "Credit",
        desc = "Credit",
        onClick = {}
    )
}