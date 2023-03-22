package fr.uge.plutus.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.uge.plutus.ui.ant.Ant
import fr.uge.plutus.ui.components.*


@Composable
fun BudgetWidget(
    title: String,
    data: List<DonutChartItem>,
    add: () -> Unit
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
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = title,
                            color = Ant.colors.secondary_text,
                            fontSize = 20.sp
                        )
                        AntActionButton(
                            type = AntActionButtonType.PRIMARY,
                            icon = Icons.Outlined.Add,
                            title = "Add",
                            onClick = add
                        )
                    }

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
                    current = item.current,
                    target = item.target,
                    color = item.color,
                    onClick = {}
                )
            }
            item {
                Spacer(modifier = Modifier.size(100.dp))
            }
        }
    )
}

/*
@Preview
@Composable
fun BudgetWidgetPreview() {
    val dataIncome =
        listOf(
            DonutChartItem(
                label = "Gift",
                current = 200.0,
                target = 450.0,
                id = 1
            ),
            DonutChartItem(
                label = "Salary",
                current = 30.0,
                target = 45.99,
                id = 2
            ),
            DonutChartItem(
                label = "Interest Money",
                current = 50.95,
                target = 99.50,
                id = 3
            )
        )

    BudgetWidget(
        data = dataIncome,
        title = "Incoming",
        add = {}
    )
}*/
