package fr.uge.plutus.layout.wallet_screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material.icons.outlined.FileCopy
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import fr.uge.plutus.data.model.Wallet
import fr.uge.plutus.storage.SettingsWallet
import fr.uge.plutus.storage.StorageViewModel
import fr.uge.plutus.ui.ant.Ant
import fr.uge.plutus.ui.components.*
import fr.uge.plutus.ui.field.AntTextField

@Composable
fun WalletScreen(
    sheetVisible: MutableState<Boolean> = remember { mutableStateOf(false) },
    viewModel: WalletViewModel = hiltViewModel(),
    storageViewModel: StorageViewModel = hiltViewModel()
) {
    val disabled = remember { mutableStateOf(true) }
    val wallet = remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    LaunchedEffect(wallet.value) {
        disabled.value = wallet.value.isEmpty() || wallet.value.isBlank()
    }

    LaunchedEffect(sheetVisible.value) {
        if (!sheetVisible.value) {
            focusManager.clearFocus()
        }
    }

    AntBottomSheetScaffold(
        sheetPeekHeight = 0.dp,
        sheetVisible = sheetVisible,
        topBar = {
            AntTopBar(
                title = "Wallets",
                sheetVisible = sheetVisible,
                trailingIcons = listOf {
                    AntActionButton(
                        icon = Icons.Outlined.Add,
                        title = "New Wallet",
                        type = AntActionButtonType.PRIMARY,
                        onClick = { sheetVisible.value = !sheetVisible.value }
                    )
                }
            )
        },
        sheetContent = {
            Column(
                modifier = Modifier.padding(Ant.spacing.default),
                verticalArrangement = Arrangement.spacedBy(Ant.spacing.default)
            ) {
                AntTextField(
                    placeHolder = "Wallet name",
                    text = wallet
                )
                CustomButton(
                    title = "Create wallet",
                    type = CustomButtonType.PRIMARY,
                    disabled = disabled,
                    onClick = {
                        viewModel.onEvent(
                            WalletEvent.CreateWallet(
                                Wallet(
                                    id = null,
                                    name = wallet.value
                                )
                            )
                        )
                        sheetVisible.value = false
                    }
                )
            }
        },
        content = {
            Column(verticalArrangement = Arrangement.spacedBy(Ant.spacing.default)) {
                val focus = remember { mutableStateOf(0) }
                AntWalletCardList(
                    items = viewModel.state.value.wallets.map { wallet ->
                        AntWalletCardItem(
                            label = wallet.name,
                            desc = wallet.id.toString(),
                            selected = SettingsWallet.current == wallet.id,
                        )
                    },
                    selected = { focus.value = it }
                )
                if (viewModel.state.value.wallets.isNotEmpty()) {
                    WalletDetailScreen(
                        wallet = viewModel.state.value.wallets[focus.value],
                        selected = SettingsWallet.current == viewModel.state.value.wallets[focus.value].id,
                        onSelect = { wallet ->
                            storageViewModel.setWallet(wallet.id)
                        }
                    )
                }
            }
        }
    )
}


@Composable
fun WalletDetailScreen(
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
                label = "Select",
                onClick = {
                    onSelect.invoke(wallet)
                }
            )
        }
    }
}