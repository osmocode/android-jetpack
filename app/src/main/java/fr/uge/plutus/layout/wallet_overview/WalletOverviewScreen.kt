package fr.uge.plutus.layout.wallet_overview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import fr.uge.plutus.navigation.NavigationRoute
import fr.uge.plutus.storage.LocalStorage
import fr.uge.plutus.ui.ant.Ant
import fr.uge.plutus.ui.components.*

@Composable
fun WalletOverviewScreen(
    navController: NavHostController,
    viewModel: WalletOverviewViewModel = hiltViewModel(),
    localStorage: LocalStorage = hiltViewModel()
) {
    val sheetVisible = remember { mutableStateOf(false) }

    AntBottomSheetScaffold(
        sheetPeekHeight = 0.dp,
        sheetVisible = sheetVisible,
        sheetContent = { /*TODO*/ },
        topBar = {
            AntTopBar(
                title = "Wallets",
                trailingIcons = listOf {
                    AntActionButton(
                        icon = Icons.Outlined.Add,
                        type = AntActionButtonType.PRIMARY,
                        title = "Add",
                        onClick = {
                            navController.navigate(NavigationRoute.NewWallet.route)
                        }
                    )
                }
            )
        },
        content = {
            Column(
                verticalArrangement = Arrangement.spacedBy(Ant.spacing.small)
            ) {
                AntWalletCardList(
                    items = viewModel.state.value.wallets.map { wallet ->
                        AntWalletCardItem(
                            label = wallet.name,
                            desc = wallet.id.toString()
                        )
                    }
                )
            }
        }
    )

}