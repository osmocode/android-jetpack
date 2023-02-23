package fr.uge.plutus.ui.field

import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import fr.uge.plutus.ui.ant.Ant
import java.util.regex.Pattern

@Composable
fun AntAmountField(
    prefix: MutableState<String>,
    amount: MutableState<Double>
) {
    val text = remember { mutableStateOf("${amount.value}") }

    BasicTextField(
        value = text.value,
        onValueChange = {
            if(Pattern.compile("\\d*\\.?\\d{0,2}").matcher(it).matches()){
                text.value = it
                val dotted = Pattern.compile("\\.").matcher(it).matches()
                if(dotted && it.length > 1)
                    amount.value = it.toDouble()
                else if(!dotted && it.isNotEmpty())
                    amount.value = it.toDouble()
                else
                    amount.value = 0.0
            }
        },
        cursorBrush = SolidColor(
            value = Ant.colors.primary_color_3
        ),
        singleLine = true,
        textStyle = LocalTextStyle.current.copy(
            color = Ant.colors.primary_color_3,
            fontSize = 50.sp,
            textAlign = TextAlign.Center
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number
        ),
        visualTransformation = AntAmountFieldPrefix(prefix.value),
        decorationBox = { input_field ->
            input_field()
        }
    )
}

class AntAmountFieldPrefix(
    private val prefix: String
): VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val out = prefix + text.text
        val prefixOffset = prefix.length

        val numberOffsetTranslator = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                return offset + prefixOffset
            }

            override fun transformedToOriginal(offset: Int): Int {
                if (offset < prefixOffset) return 0
                return offset - prefixOffset
            }
        }
        return TransformedText(AnnotatedString(out), numberOffsetTranslator)
    }
}

@Preview
@Composable
fun AntAmountFieldPreview() {
    val prefix = remember { mutableStateOf("$") }
    val amount = remember { mutableStateOf(10.0) }
    AntAmountField(
        prefix = prefix,
        amount = amount
    )
}