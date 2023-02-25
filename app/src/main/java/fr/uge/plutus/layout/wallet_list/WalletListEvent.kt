package fr.uge.plutus.layout.wallet_list

import fr.uge.plutus.data.model.Wallet

sealed class WalletListEvent {
    data class CreateWallet(val wallet: Wallet) : WalletListEvent()
    data class DeleteWallet(val wallet: Wallet) : WalletListEvent()
}