package fr.uge.plutus.ui.input

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Event
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.uge.plutus.ui.ant.Ant
import java.text.DateFormat
import java.util.*

@Composable
fun AntDateInput(
    timestamp: Long,
    onClick: () -> Unit
) {
    val formatter = DateFormat.getDateInstance()
    val date = formatter.format(Date(timestamp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = Ant.shapes.default)
            .background(color = Ant.colors.gray_1,)
            .clickable(onClick = onClick)
            .padding(
                vertical = Ant.spacing.small,
                horizontal = Ant.spacing.default
            ),
        horizontalArrangement = Arrangement.spacedBy(Ant.spacing.default),
        verticalAlignment = Alignment.CenterVertically
    ){
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(
                    color = Ant.colors.gray_4,
                    shape = CircleShape
                )
        ) {
            Icon(
                modifier = Modifier.align(Alignment.Center),
                imageVector = Icons.Outlined.Event,
                contentDescription = "Date Icon",
                tint = Ant.colors.gray_8
            )
        }
        Column {
            Text(
                text = "Set date",
                color = Ant.colors.secondary_text
            )
            Text(
                text = "$date",
                fontWeight = FontWeight.SemiBold,
                color = Ant.colors.secondary_text
            )
        }
    }
}

@Preview
@Composable
fun AntDateInputPreview(

) {
    AntDateInput(
        timestamp = 60000,
        onClick = {}
    )
}