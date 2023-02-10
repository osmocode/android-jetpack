package fr.uge.plutus.layout.history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import fr.uge.plutus.ui.components.AntTextField
import fr.uge.plutus.widget.TransactionPager

@Composable
fun HistoryScreen(
    navController: NavHostController
) {
    val context = LocalContext.current.applicationContext
    val viewModel = viewModel<HistoryViewModel>(factory = HistoryViewModelFactory(context))

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = "History",
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            modifier = Modifier.padding(15.dp)
        )
        AntTextField()
        TransactionPager(
            navController = navController
        )
    }
}