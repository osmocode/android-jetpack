package fr.uge.plutus.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import fr.uge.plutus.ui.ant.Ant

@Composable
fun AntWalletAction(
    icon: ImageVector,
    label: String,
    onClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(Ant.spacing.small),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(shape = CircleShape)
                .background(color = Ant.colors.gray_5)
                .clickable(onClick = onClick)
        ) {
            Icon(
                modifier = Modifier.align(Alignment.Center),
                imageVector = icon,
                contentDescription = null
            )
        }
        Text(
            text = label,
            color = Ant.colors.primary_text,
            textAlign = TextAlign.Center
        )
    }

}