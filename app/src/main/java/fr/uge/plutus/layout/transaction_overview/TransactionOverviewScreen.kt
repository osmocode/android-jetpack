package fr.uge.plutus.layout.transaction_overview

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import fr.uge.plutus.storage.LocalStorageWallet

@Composable
fun TransactionOverviewScreen(
    navController: NavHostController,
    viewModel: TransactionOverviewViewModel = hiltViewModel()
) {
    val current = LocalStorageWallet.current

}