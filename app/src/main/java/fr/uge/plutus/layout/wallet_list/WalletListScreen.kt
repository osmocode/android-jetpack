package fr.uge.plutus.layout.wallet_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import fr.uge.plutus.data.model.Wallet
import fr.uge.plutus.storage.LocalStorage
import fr.uge.plutus.storage.LocalStorageWallet
import fr.uge.plutus.ui.ant.Ant
import fr.uge.plutus.ui.components.*
import fr.uge.plutus.ui.field.AntTextField

@Composable
fun WalletListScreen(
    navController: NavHostController,
    viewModel: WalletListViewModel = hiltViewModel(),
    localStorage: LocalStorage = hiltViewModel()
) {
    val sheetVisible = remember { mutableStateOf(false) }
    val walletName = remember { mutableStateOf("") }
    val disabled = remember { mutableStateOf(true) }

    LaunchedEffect(walletName.value) {
        disabled.value = walletName.value.isEmpty() || walletName.value.isBlank()
    }

    AntBottomSheetScaffold(
        sheetPeekHeight = 0.dp,
        sheetVisible = sheetVisible,
        sheetContent = {
            Column(
                modifier = Modifier.padding(Ant.spacing.default),
                verticalArrangement = Arrangement.spacedBy(Ant.spacing.default)
            ) {
                // Ajoutez les champs d'entrée de texte pour le nom et le solde du portefeuille
                AntTextField(
                    placeHolder = "Wallet Name",
                    onChange = { walletName.value = it }
                )

                // Ajoutez un bouton de création pour créer un nouveau portefeuille
                CustomButton(
                    title = "Create Wallet",
                    type = CustomButtonType.PRIMARY,
                    disabled = disabled,
                    onClick = {
                        // Code pour créer un nouveau portefeuille
                        viewModel.onEvent(
                            WalletListEvent.CreateWallet(
                                wallet = Wallet(
                                    id = null,
                                    name = walletName.value
                                )
                            )
                        )
                        sheetVisible.value = false
                    }
                )
            }
        },
        topBar = {
            AntTopBar(
                title = "Wallets",
                leadingIcons = listOf {
                    if (navController.backQueue.size > 2) {
                        AntActionButton(
                            icon = Icons.Outlined.ArrowBack,
                            onClick = {
                                navController.popBackStack()
                            }
                        )
                    }
                },
                trailingIcons = listOf {
                    AntActionButton(
                        icon = Icons.Outlined.Add,
                        title = "New Wallet",
                        type = AntActionButtonType.PRIMARY,
                        onClick = {
                            sheetVisible.value = !sheetVisible.value
                        }
                    )
                }
            )
        }
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(Ant.spacing.default)
        ) {
            Text(
                modifier = Modifier.padding(horizontal = Ant.spacing.default),
                text = "Create a new wallet",
                color = Ant.colors.primary_text
            )
            AntTextField(
                modifier = Modifier.padding(horizontal = Ant.spacing.default),
                leadingIcon = Icons.Outlined.Search,
                onChange = {}
            )
            LazyColumn(
                modifier = Modifier.padding(horizontal = Ant.spacing.default),
            ) {
                items(viewModel.state.value.wallets) { wallet ->
                    AntCard(
                        title = wallet.name,
                        description = "Wallet id: ${wallet.id}",
                        extras = if (LocalStorageWallet.current == wallet.id) "Current wallet" else "",
                        icon = Icons.Outlined.AccountBalanceWallet,
                        leadingIcon = if (LocalStorageWallet.current != wallet.id) listOf(
                            AntCardActionItem(
                                icon = Icons.Outlined.Delete,
                                color = Ant.colors.primary_text,
                                onClick = {
                                    viewModel.onEvent(
                                        event = WalletListEvent.DeleteWallet(
                                            wallet = wallet
                                        )
                                    )
                                },
                            )
                        ) else listOf(
                            AntCardActionItem(
                                icon = Icons.Outlined.Delete,
                                color = Ant.colors.primary_text,
                                onClick = {},
                            )
                        ),
                        trailingIcon = listOf(
                            AntCardActionItem(
                                icon = Icons.Outlined.FileCopy,
                                color = Ant.colors.primary_text,
                                onClick = {},
                            ),
                            AntCardActionItem(
                                icon = Icons.Outlined.Done,
                                color = Ant.colors.primary_text,
                                onClick = {
                                    localStorage.setWallet(wallet.id)
                                },
                            )
                        )
                    )
                }
            }
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun WalletListPagePreview(

) {
    val navController = rememberNavController()
    WalletListScreen(
        navController = navController
    )
}
