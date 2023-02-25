package fr.uge.plutus.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.uge.plutus.ui.ant.Ant
import fr.uge.plutus.ui.components.AntBudgetCard
import fr.uge.plutus.ui.components.DonutChartItem
import fr.uge.plutus.ui.components.DonutChart


@Composable
fun BudgetWidget(
    title: String,
    data: List<DonutChartItem>
) {

    LazyColumn(
        contentPadding = PaddingValues(Ant.spacing.default),
        verticalArrangement = Arrangement.Top,
        content = {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Ant.spacing.default)
                        .background(
                            color = Ant.colors.gray_1,
                            shape = Ant.shapes.default
                        )
                        .padding(Ant.spacing.default),
                    verticalArrangement = Arrangement.spacedBy(Ant.spacing.default)
                ) {
                    Text(
                        text = title,
                        color = Ant.colors.secondary_text,
                        fontSize = 20.sp
                    )
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        DonutChart(items = data)
                    }
                }
            }
            items(data) { item ->
                AntBudgetCard(
                    label = item.label,
                    current = item.value,
                    target = 1000.0,
                    onClick = {}
                )
            }
            item {
                Spacer(modifier = Modifier.size(100.dp))
            }
        }
    )
}

@Preview
@Composable
fun BudgetWidgetPreview() {
    val dataIncome =
        listOf(
            DonutChartItem(
                label = "Gift",
                value = 450.0
            ),
            DonutChartItem(
                label = "Salary",
                value = 45.99
            ),
            DonutChartItem(
                label = "Interest Money",
                value = 99.50
            )
        )

    BudgetWidget(
        data = dataIncome,
        title = "Incoming"
    )


}