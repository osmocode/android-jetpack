package fr.uge.plutus.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.uge.plutus.ui.ant.Ant
import fr.uge.plutus.ui.theme.*
import kotlin.math.PI
import kotlin.math.atan2

data class DonutChartItem(
    val label: String,
    val value: Double,
    val isTapped: Boolean = false
)

@Composable
fun DonutChart(
    items: List<DonutChartItem>,
    donutBarWidth: Dp = 15.dp,
) {
    val colors = listOf(
        Purple200,
        Purple500,
        Teal200,
        Purple700,
        Blue
    )

    val data = remember { mutableStateOf(items) }

    var circleCenter by remember { mutableStateOf(Offset.Zero) }

    val radius = donutBarWidth * 4

    val sumValue = data.value.sumOf { it.value }
    val anglePerValue = 360f / sumValue
    var currentAngle = 0f

    data.value.forEachIndexed { index, donutChartInput ->
        val scale = if (donutChartInput.isTapped) 1.17f else 1.0f
        val stroke = if (donutChartInput.isTapped) donutBarWidth * 2f else donutBarWidth
        val angleToDraw = donutChartInput.value * anglePerValue
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Canvas(
                modifier = Modifier
                    .size(180.dp)
                    .aspectRatio(1f)
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onTap = { offset ->
                                val tapAngleInDegrees = (-atan2(
                                    x = circleCenter.y - offset.y,
                                    y = circleCenter.x - offset.x
                                ) * (180f / PI).toFloat() - 90f).mod(360f)

                                var currAngle = 0f
                                data.value.forEach { donutChartInput ->
                                    currAngle += (donutChartInput.value * anglePerValue).toFloat()
                                    if (tapAngleInDegrees < currAngle) {
                                        val description = donutChartInput.label
                                        data.value = data.value.map {
                                            if (description == it.label) {
                                                it.copy(isTapped = !it.isTapped)
                                            } else {
                                                it.copy(isTapped = false)
                                            }
                                        }
                                        return@detectTapGestures
                                    }
                                }
                            }
                        )
                    }
            ) {
                circleCenter = Offset(x = size.width / 2f, y = size.height / 2f)
                scale(scale) {
                    drawArc(
                        color = colors[(index % colors.size)],
                        startAngle = currentAngle,
                        sweepAngle = angleToDraw.toFloat(),
                        useCenter = false,
                        size = Size(
                            width = radius.toPx() * 2f,
                            height = radius.toPx() * 2f
                        ),
                        topLeft = Offset(
                            (size.width - radius.toPx() * 2f) / 2f,
                            (size.height - radius.toPx() * 2f) / 2f
                        ),
                        style = Stroke(
                            width = stroke.toPx()
                        )
                    )
                    currentAngle += angleToDraw.toFloat()
                }
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = if (donutChartInput.isTapped) "${donutChartInput.value}€" else "",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = Ant.colors.primary_text
                )
                Text(
                    text = if (donutChartInput.isTapped) donutChartInput.label else "",
                    fontSize = 14.sp,
                    color = Ant.colors.secondary_text
                )
            }
        }
    }
}


@Preview
@Composable
fun DonutChartPreview(

) {
    val data = remember {
        listOf(
            DonutChartItem(
                label = "Sample-1",
                value = 200.0
            ),
            DonutChartItem(
                label = "Sample-2",
                value = 150.0
            ),
            DonutChartItem(
                label = "Sample-3",
                value = 100.0
            ),
            DonutChartItem(
                label = "Sample-4",
                value = 50.0
            ),
            DonutChartItem(
                label = "Sample-5",
                value = 20.0
            )
        )
    }

    DonutChart(items = data)
}