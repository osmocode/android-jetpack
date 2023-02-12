package fr.uge.plutus.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChevronLeft
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.uge.plutus.ui.ant.Ant

enum class CustomButtonType {
    PRIMARY, DEFAULT, LINK
}

@Composable
fun CustomButton(
    title: String,
    type: CustomButtonType = CustomButtonType.DEFAULT,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {

    Row(
        modifier = modifier
            .clip(
                shape = RoundedCornerShape(10.dp)
            )
            .border(
                width = 1.dp,
                color = if (type == CustomButtonType.DEFAULT) Ant.colors.button_color else Color.Transparent,
                shape = RoundedCornerShape(10.dp)
            )
            .clickable(
                onClick = onClick
            )
            .fillMaxWidth()
            .background(
                color = CustomButtonBackgroundColor(
                    type = type
                ),
            )
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ){
        if (leadingIcon != null) {
            Icon(
                imageVector = leadingIcon,
                contentDescription = "Leading Icon",
                tint = CustomButtonColor(
                    type = type
                )
            )
        }
        Text(
            text = title,
            color = CustomButtonColor(
                type = type
            )
        )
        if (trailingIcon != null) {
            Icon(
                imageVector = trailingIcon,
                contentDescription = "Trailing Icon",
                tint = CustomButtonColor(
                    type = type
                )
            )
        }
    }
}

@Composable
fun CustomButtonBackgroundColor(
    type: CustomButtonType
): Color {
    return when (type) {
        CustomButtonType.PRIMARY -> Ant.colors.button_color
        CustomButtonType.DEFAULT -> Color.White
        CustomButtonType.LINK -> Color.Transparent
    }
}

@Composable
fun CustomButtonColor(
    type: CustomButtonType
): Color {
    return when (type) {
        CustomButtonType.PRIMARY -> Color.White
        CustomButtonType.DEFAULT -> Ant.colors.button_color
        CustomButtonType.LINK -> Ant.colors.button_color
    }
}

@Preview
@Composable
fun PrimaryCustomButtonPreview(

) {
    CustomButton(
        title = "Button",
        type = CustomButtonType.PRIMARY,
        trailingIcon = Icons.Outlined.ChevronRight,
    )
}

@Preview
@Composable
fun DefaultCustomButtonPreview(

) {
    CustomButton(
        title = "Previous",
        leadingIcon = Icons.Outlined.ChevronLeft
    )
}

@Preview
@Composable
fun LinkCustomButtonPreview(

) {
    CustomButton(
        title = "Button",
        type = CustomButtonType.LINK,
        leadingIcon = Icons.Outlined.ChevronLeft,
        trailingIcon = Icons.Outlined.ChevronRight
    )
}