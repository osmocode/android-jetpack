package fr.uge.plutus.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.uge.plutus.ui.ant.Ant

enum class AntActionButtonType {
    PRIMARY, DEFAULT, LINK
}

@Composable
fun AntActionButton(
    icon: ImageVector,
    title: String,
    type: AntActionButtonType = AntActionButtonType.DEFAULT,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
       modifier = modifier
           .clip(shape = CircleShape)
           .clickable(onClick = onClick)
           .background(
               color = AntActionButtonBackgroundColor(
                   type = type
               )
           )
    ){
        Row(
            modifier = Modifier.padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = AntActionButtonColor(
                    type = type
                )
            )
            Text(
                modifier = Modifier.padding(
                    end = 5.dp,
                ),
                text = title,
                color = AntActionButtonColor(
                    type = type
                )
            )
        }
    }
}

@Composable
fun AntActionButtonBackgroundColor(
    type: AntActionButtonType
): Color {
    return when (type) {
        AntActionButtonType.PRIMARY -> Ant.colors.button_color
        AntActionButtonType.DEFAULT -> Color.White
        AntActionButtonType.LINK -> Color.Transparent
    }
}

@Composable
fun AntActionButtonColor(
    type: AntActionButtonType
): Color {
    return when (type) {
        AntActionButtonType.PRIMARY -> Color.White
        AntActionButtonType.DEFAULT -> Ant.colors.button_color
        AntActionButtonType.LINK -> Ant.colors.button_color
    }
}

@Preview
@Composable
fun DefaultAntActionButtonPreview(

) {
    AntActionButton(
        icon = Icons.Outlined.Add,
        title = "New Transaction",
        onClick = { /*TODO*/ }
    )
}

@Preview
@Composable
fun PrimaryAntActionButtonPreview(

) {
    AntActionButton(
        type = AntActionButtonType.PRIMARY,
        icon = Icons.Outlined.Add,
        title = "New Transaction",
        onClick = { /*TODO*/ }
    )
}
