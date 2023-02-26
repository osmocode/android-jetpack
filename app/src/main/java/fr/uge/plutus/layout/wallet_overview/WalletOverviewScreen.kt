package fr.uge.plutus.layout.wallet_overview

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import fr.uge.plutus.data.model.Wallet
import fr.uge.plutus.navigation.NavigationRoute
import fr.uge.plutus.storage.LocalStorage
import fr.uge.plutus.storage.LocalStorageWallet
import fr.uge.plutus.ui.ant.Ant
import fr.uge.plutus.ui.components.*

@Composable
fun WalletOverviewScreen(
    navController: NavHostController,
    viewModel: WalletOverviewViewModel = hiltViewModel(),
    localStorage: LocalStorage = hiltViewModel()
) {
    val sheetVisible = remember { mutableStateOf(false) }
    val content = remember { mutableStateOf(0) }

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
                verticalArrangement = Arrangement.spacedBy(Ant.spacing.default)
            ) {
                AntWalletCardList(
                    items = viewModel.state.value.wallets.map { wallet ->
                        AntWalletCardItem(
                            label = wallet.name,
                            desc = wallet.id.toString(),
                            selected = LocalStorageWallet.current == wallet.id
                        )
                    },
                    selected = { content.value = it }
                )

                if (viewModel.state.value.wallets.isNotEmpty()) {
                    WalletOverviewScreenContent(
                        wallet = viewModel.state.value.wallets[content.value],
                        selected = LocalStorageWallet.current == viewModel.state.value.wallets[content.value].id,
                        onSelect = { w ->
                            //navController.popBackStack()
                            localStorage.setWallet(w.id)
                        }
                    )
                }
            }
        }
    )
}

@Composable
fun WalletOverviewScreenContent(
    wallet: Wallet,
    selected: Boolean,
    onDelete: (wallet: Wallet) -> Unit = {},
    onCopy: (wallet: Wallet) -> Unit = {},
    onSelect: (wallet: Wallet) -> Unit = {}
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        AntWalletAction(
            icon = Icons.Outlined.Delete,
            label = "Delete\nWallet",
            onClick = {
                onDelete.invoke(wallet)
            }
        )
        AntWalletAction(
            icon = Icons.Outlined.FileCopy,
            label = "Duplicate\nContent",
            onClick = {
                onCopy.invoke(wallet)
            }
        )
        if (!selected) {
            AntWalletAction(
                icon = Icons.Outlined.Done,
                label = "${wallet.id}",
                onClick = {
                    onSelect.invoke(wallet)
                }
            )
        }
    }
}