package fr.uge.plutus.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
    disabled: MutableState<Boolean> = mutableStateOf(false),
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .clip(shape = Ant.shapes.default)
            .border(
                width = 1.dp,
                color = if (type == CustomButtonType.DEFAULT) Ant.colors.primary_color_5 else Color.Transparent,
                shape = Ant.shapes.default
            )
            .clickable(
                enabled = !disabled.value,
                onClick = onClick
            )
            .fillMaxWidth()
            .background(color = CustomButtonBackgroundColor(type = type, disabled = disabled))
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        if (leadingIcon != null) {
            Icon(
                imageVector = leadingIcon,
                contentDescription = "Leading Icon",
                tint = CustomButtonColor(type = type, disabled = disabled),
                modifier = Modifier.padding(end = Ant.spacing.small)
            )
        }
        Text(
            text = title,
            color = CustomButtonColor(type = type, disabled = disabled)
        )
        if (trailingIcon != null) {
            Icon(
                imageVector = trailingIcon,
                contentDescription = "Trailing Icon",
                tint = CustomButtonColor(type = type, disabled = disabled),
                modifier = Modifier.padding(start = Ant.spacing.small)
            )
        }
    }
}

@Composable
fun CustomButtonBackgroundColor(
    type: CustomButtonType,
    disabled: MutableState<Boolean>,
): Color {
    return when(type) {
        CustomButtonType.DEFAULT -> if (disabled.value) Ant.colors.gray_9 else Ant.colors.gray_1
        CustomButtonType.PRIMARY -> if (disabled.value) Ant.colors.primary_color_3 else Ant.colors.primary_color_5
        CustomButtonType.LINK -> Color.Transparent
    }
}

@Composable
fun CustomButtonColor(
    type: CustomButtonType,
    disabled: MutableState<Boolean>,
): Color {
    return when(type) {
        CustomButtonType.DEFAULT -> if (disabled.value) Ant.colors.primary_color_3 else Ant.colors.primary_color_5
        CustomButtonType.PRIMARY -> if (disabled.value) Ant.colors.gray_8 else Ant.colors.gray_1
        CustomButtonType.LINK -> if (disabled.value) Ant.colors.gray_8 else Ant.colors.primary_color_5
    }
}

@Preview
@Composable
fun DefaultCustomButtonPreview(

) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        val state = remember { mutableStateOf(true) }
        CustomButton(
            title = "Previous",
            leadingIcon = Icons.Outlined.ArrowBack,
            onClick = {}
        )
        CustomButton(
            title = "Previous",
            leadingIcon = Icons.Outlined.ArrowBack,
            disabled = state,
            onClick = {}
        )
    }
}

@Preview
@Composable
fun PrimaryCustomButtonPreview(

) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        val state = remember { mutableStateOf(true) }
        CustomButton(
            title = "Continue",
            type = CustomButtonType.PRIMARY,
            trailingIcon = Icons.Outlined.ArrowForward,
            onClick = {}
        )
        CustomButton(
            title = "Continue",
            type = CustomButtonType.PRIMARY,
            trailingIcon = Icons.Outlined.ArrowForward,
            disabled = state,
            onClick = {}
        )
    }
}


@Preview
@Composable
fun LinkCustomButtonPreview(

) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        val state = remember { mutableStateOf(true) }
        CustomButton(
            title = "Double",
            trailingIcon = Icons.Outlined.ArrowForward,
            leadingIcon = Icons.Outlined.ArrowBack,
            type = CustomButtonType.LINK,
            onClick = {}
        )
        CustomButton(
            title = "Double",
            trailingIcon = Icons.Outlined.ArrowForward,
            leadingIcon = Icons.Outlined.ArrowBack,
            type = CustomButtonType.LINK,
            disabled = state,
            onClick = {}
        )
    }
}