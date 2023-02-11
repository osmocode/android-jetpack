package fr.uge.plutus.ui.components

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
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.uge.plutus.ui.ant.Ant

@Composable
fun AntTextField(
    modifier: Modifier = Modifier,
    placeHolder: String = "Placeholder"
) {
    var text = remember { mutableStateOf("") }
    val interactionSource = remember { MutableInteractionSource() }
    Box(
        modifier = Modifier.padding(horizontal = 15.dp)
    ) {
        BasicTextField(
            modifier = modifier
                .background(
                    color = Ant.colors.input_backgroundColor,
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
                value = Ant.colors.input_cursorColor
            ),
            textStyle = LocalTextStyle.current.copy(
                color = Ant.colors.input_textColor
            ),
            decorationBox = { text_field ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon",
                        tint = Ant.colors.input_textColor,
                    )
                    Box(
                        modifier = Modifier.weight(1f)
                    ) {
                        if (text.value.isEmpty()) {
                            Text(
                                text = placeHolder,
                                style = LocalTextStyle.current.copy(
                                    color = Ant.colors.input_placeholderColor,
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
                                    color = Ant.colors.input_textColor,
                                    shape = CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Clear Icon",
                                tint = Ant.colors.input_backgroundColor,
                                modifier = Modifier
                                    .size(18.dp)
                                    .clickable(
                                        indication = null,
                                        interactionSource = interactionSource
                                    ){
                                        text.value = ""
                                    }
                            )
                        }
                    }
                }
            }
        )
    }

    /*
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .background(
                color = Ant.colors.nav_background,
                shape = RoundedCornerShape(10.dp)
            ),
        value = text.value,
        onValueChange = {
            text.value = it
        },
        placeholder = {
            Text(
                text = "Search...",
            )
        },
        singleLine = true,
        leadingIcon = {
            Icon(
                modifier = Modifier.padding(top = 0.dp),
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon",
            )
        },
        trailingIcon = {
            IconButton(
                onClick = {}
            ) {
                Icon(
                    modifier = Modifier.padding(top = 0.dp),
                    imageVector = Icons.Default.Close,
                    contentDescription = "Clear Icon",
                )
            }
        },
        colors = TextFieldDefaults.textFieldColors(
            textColor = Ant.colors.input_textColor,
            leadingIconColor = Ant.colors.input_leadingIconColor,
            trailingIconColor = Ant.colors.input_trailingIconColor,
            cursorColor = Ant.colors.input_cursorColor,
            placeholderColor = Ant.colors.input_placeholderColor,
            focusedIndicatorColor = Ant.colors.input_focusedIndicatorColor,
            unfocusedIndicatorColor = Ant.colors.input_unfocusedIndicatorColor,
            backgroundColor = Ant.colors.input_backgroundColor
        )
    )
    */
}

@Preview
@Composable
fun AntTextFieldPreview(

) {
    AntTextField()
}