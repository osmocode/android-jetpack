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
    title: String = "",
    type: AntActionButtonType = AntActionButtonType.DEFAULT,
    onClick: () -> Unit,
) {
    Box(
       modifier = Modifier
           .clip(shape = CircleShape)
           .clickable(onClick = onClick)
           .background(
               color = antActionButtonBackgroundColor(
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
                tint = antActionButtonColor(
                    type = type
                )
            )
            if(title.isNotEmpty()) {
                Text(
                    modifier = Modifier.padding(
                        end = 5.dp,
                    ),
                    text = title,
                    color = antActionButtonColor(
                        type = type
                    )
                )
            }
        }
    }
}

@Composable
fun antActionButtonBackgroundColor(
    type: AntActionButtonType
): Color {
    return when (type) {
        AntActionButtonType.PRIMARY -> Ant.colors.primary_color_5
        AntActionButtonType.DEFAULT -> Ant.colors.background
        AntActionButtonType.LINK -> Color.Transparent
    }
}

@Composable
fun antActionButtonColor(
    type: AntActionButtonType
): Color {
    return when (type) {
        AntActionButtonType.PRIMARY -> Color.White
        AntActionButtonType.DEFAULT -> Ant.colors.gray_10
        AntActionButtonType.LINK -> Ant.colors.primary_color_5
    }
}

@Preview
@Composable
fun DefaultAntActionButtonPreview(

) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        AntActionButton(
            icon = Icons.Outlined.Add,
            title = "Default",
            onClick = { /*TODO*/ }
        )
        AntActionButton(
            icon = Icons.Outlined.Add,
            onClick = { /*TODO*/ }
        )
    }
}

@Preview
@Composable
fun PrimaryAntActionButtonPreview(

) {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        AntActionButton(
            type = AntActionButtonType.PRIMARY,
            icon = Icons.Outlined.Add,
            title = "New Transaction",
            onClick = { /*TODO*/ }
        )

        AntActionButton(
            type = AntActionButtonType.PRIMARY,
            icon = Icons.Outlined.Add,
            onClick = { /*TODO*/ }
        )
    }
}



@Preview
@Composable
fun LinkAntActionButtonPreview(){
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        AntActionButton(
            type = AntActionButtonType.LINK,
            icon = Icons.Outlined.Add,
            title = "Link",
            onClick = { /*TODO*/ }
        )

        AntActionButton(
            type = AntActionButtonType.LINK,
            icon = Icons.Outlined.Add,
            onClick = { /*TODO*/ }
        )
    }
}
