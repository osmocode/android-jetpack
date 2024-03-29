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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.uge.plutus.ui.ant.Ant
import kotlin.math.PI
import kotlin.math.atan2

data class DonutChartItem(
    val label: String,
    val current: Double,
    val target: Double,
    val id: Long,
    val color: Color,
    val isTapped: Boolean = false
)

@Composable
fun DonutChartEmpty(
    donutBarWidth: Dp = 15.dp
) {
    val radius = donutBarWidth * 4
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .size(180.dp)
                .aspectRatio(1f)
        ) {
            drawCircle(
                color = Color.Gray,
                radius = radius.toPx(),
                style = Stroke(width = donutBarWidth.toPx())
            )
        }
        Text(
            text = "Empty budget",
            fontSize = 14.sp,
            color = Ant.colors.secondary_text
        )
    }

}

@Composable
fun DonutChartWithBudget(
    items: List<DonutChartItem>,
    donutBarWidth: Dp = 15.dp,
) {
    val data = remember { mutableStateOf(items) }

    var circleCenter by remember { mutableStateOf(Offset.Zero) }

    val radius = donutBarWidth * 4

    val sumValue = data.value.sumOf { it.current }
    val anglePerValue = 360f / sumValue
    var currentAngle = 0f

    data.value.forEachIndexed { index, donutChartItem ->
        val scale = if (donutChartItem.isTapped) 1.17f else 1.0f
        val stroke = if (donutChartItem.isTapped) donutBarWidth * 2f else donutBarWidth
        val angleToDraw = donutChartItem.current * anglePerValue
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
                                data.value.forEach { donutChartItem ->
                                    currAngle += (donutChartItem.current * anglePerValue).toFloat()
                                    if (tapAngleInDegrees < currAngle) {
                                        val id = donutChartItem.id
                                        data.value = data.value.map {
                                            if (id == it.id) {
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
                circleCenter = Offset(
                    x = size.width / 2f,
                    y = size.height / 2f
                )
                scale(scale) {
                    drawArc(
                        color = donutChartItem.color,
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
                    text = if (donutChartItem.isTapped) "${donutChartItem.current}€" else "",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = Ant.colors.primary_text
                )
                Text(
                    text = if (donutChartItem.isTapped) donutChartItem.label else "",
                    fontSize = 14.sp,
                    color = Ant.colors.secondary_text
                )
            }
        }
    }
}


@Composable
fun DonutChart(
    items: List<DonutChartItem>,
) {
    val exist = items.any { item -> item.current > 0.0 }

    if (items.isEmpty() || !exist) {
        DonutChartEmpty()
    } else {
        DonutChartWithBudget(items = items)
    }
}


/*
@Preview
@Composable
fun DonutChartPreview(

) {
    val data = remember {
        listOf(
            DonutChartItem(
                label = "Sample-1",
                current = 130.0,
                target = 200.0,
                id = 1,
            ),
            DonutChartItem(
                label = "Sample-2",
                current = 70.0,
                target = 150.0,
                id = 2
            ),
            DonutChartItem(
                label = "Sample-3",
                current = 50.0,
                target = 100.0,
                id = 3
            ),
            DonutChartItem(
                label = "Sample-4",
                current = 30.0,
                target = 50.0,
                id = 4
            ),
            DonutChartItem(
                label = "Sample-5",
                current = 20.0,
                target = 20.0,
                id = 5
            )
        )
    }

    DonutChart(items = data)
}*/
