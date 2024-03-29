package fr.uge.plutus.layout.wallet_screen

import fr.uge.plutus.data.model.Wallet

sealed class WalletEvent {
    data class CreateWallet(val wallet: Wallet) : WalletEvent()

    data class UpdateWallet(val wallet: Wallet) : WalletEvent()
}