package fr.uge.plutus.ui.field

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import fr.uge.plutus.ui.ant.Ant

@Composable
fun AntDescField(
    desc: MutableState<String>,
    placeholder: String
) {
    BasicTextField(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 200.dp),
        value = desc.value,
        onValueChange = {
            desc.value = it
        },
        cursorBrush = SolidColor(
            value = Ant.colors.primary_text
        ),
        singleLine = false,
        textStyle = LocalTextStyle.current.copy(
            color = Ant.colors.primary_text,
            textAlign = TextAlign.Left
        ),
        decorationBox = { input_field ->
            Box(
                modifier = Modifier
                    .clip(Ant.shapes.default)
                    .background(color = Ant.colors.gray_1)
                    .padding(Ant.spacing.default)
            ) {
                input_field()
                if (desc.value.isEmpty()) {
                    Text(
                        text = placeholder,
                        color = Ant.colors.secondary_text
                    )
                }
            }
        }
    )
}