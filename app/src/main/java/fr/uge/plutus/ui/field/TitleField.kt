package fr.uge.plutus.ui.field

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import fr.uge.plutus.ui.ant.Ant

@Composable
fun AntTitleField(
    title: MutableState<String>,
    placeholder: String
) {
    BasicTextField(
        modifier = Modifier.fillMaxWidth(),
        value = title.value,
        onValueChange = {
            title.value = it
        },
        cursorBrush = SolidColor(
            value = Ant.colors.primary_text
        ),
        singleLine = true,
        textStyle = LocalTextStyle.current.copy(
            color = Ant.colors.primary_text,
            fontSize = 20.sp,
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
                if (title.value.isEmpty()) {
                    Text(
                        text = placeholder,
                        fontSize = 20.sp,
                        color = Ant.colors.secondary_text
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun AntTitleFieldPreview(

) {
    val title = remember { mutableStateOf("") }
    AntTitleField(
        title = title,
        placeholder = "Placeholder"
    )
}