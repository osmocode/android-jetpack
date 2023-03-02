package fr.uge.plutus.layout.wallet_screen

import fr.uge.plutus.data.model.Wallet

data class WalletState(
    val wallets: List<Wallet> = emptyList()
)