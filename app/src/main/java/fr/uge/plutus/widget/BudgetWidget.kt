package fr.uge.plutus.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import fr.uge.plutus.ui.ant.Ant
import fr.uge.plutus.ui.components.DonutChartArc
import fr.uge.plutus.ui.components.DonutChart


@Composable
fun BudgetWidget(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    title: String,
    data: List<DonutChartArc>
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Ant.colors.gray_1,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(15.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = title,
            color = Ant.colors.secondary_text,
            fontSize = 32.sp
        )
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            DonutChart(items = data)
        }

        /*for ((label, price) in data) {
            AntCard(
                title = label,
                description = "23 fév 2023",
                extras = "$price€"
            )
        }*/
    }
}

@Preview
@Composable
fun BudgetWidgetPreview() {
    val navController = rememberNavController()

    val dataIncome =
        listOf(
            DonutChartArc(
                description = "Gift",
                value = 450.0
            ),
            DonutChartArc(
                description = "Salary",
                value = 45.99
            ),
            DonutChartArc(
                description = "Interest Money",
                value = 99.50
            )
        )


    BudgetWidget(
        navController = navController,
        data = dataIncome,
        title = "Income"
    )


}