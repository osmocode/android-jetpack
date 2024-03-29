package fr.uge.plutus.ui.field

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.uge.plutus.ui.ant.Ant

@Composable
fun AntTextField(
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector? = null,
    placeHolder: String = "Placeholder",
    text: MutableState<String>
) {
    val interactionSource = remember { MutableInteractionSource() }
    BasicTextField(
        modifier = modifier
            .background(
                color = Ant.colors.gray_5,
                shape = RoundedCornerShape(5.dp)
            )
            .fillMaxWidth()
            .padding(
                vertical = 5.dp,
                horizontal = 10.dp
            ),
        value = text.value,
        onValueChange = {
            text.value = it
        },
        singleLine = true,
        cursorBrush = SolidColor(
            value = Ant.colors.gray_9
        ),
        textStyle = LocalTextStyle.current.copy(
            color = Ant.colors.gray_9
        ),
        decorationBox = { text_field ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                if (leadingIcon != null) {
                    Icon(
                        imageVector = leadingIcon,
                        contentDescription = null,
                        tint = Ant.colors.gray_9,
                    )
                }
                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    if (text.value.isEmpty()) {
                        Text(
                            text = placeHolder,
                            style = LocalTextStyle.current.copy(
                                color = Ant.colors.secondary_text,
                            )
                        )
                    }
                    text_field()
                }
                AnimatedVisibility(
                    visible = text.value.isNotEmpty(),
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .background(
                                color = Ant.colors.gray_9,
                                shape = CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Clear Icon",
                            tint = Ant.colors.gray_1,
                            modifier = Modifier
                                .size(18.dp)
                                .clickable(
                                    indication = null,
                                    interactionSource = interactionSource
                                ) {
                                    text.value = ""
                                }
                        )
                    }
                }
            }
        }
    )
}

@Preview
@Composable
fun AntTextFieldPreview(

) {
    Column(
        verticalArrangement = Arrangement.spacedBy(Ant.spacing.default)
    ) {
        AntTextField(
            leadingIcon = Icons.Outlined.Search,
            text = remember { mutableStateOf("") }
        )
        AntTextField(
            text = remember { mutableStateOf("") }
        )
    }

}