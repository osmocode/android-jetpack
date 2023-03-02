package fr.uge.plutus.layout.home_screen.layout

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import fr.uge.plutus.layout.home_screen.HomeViewModel
import fr.uge.plutus.ui.ant.Ant
import fr.uge.plutus.widget.TransactionWidget


@Composable
fun HomeLayout(
    navController: NavHostController,
    viewModel: HomeViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Ant.spacing.default),
        verticalArrangement = Arrangement.spacedBy(Ant.spacing.default)
    ) {
        TransactionWidget(
            navController = navController,
            transaction = viewModel.state.value.transactions,
        )
    }
}