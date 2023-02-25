package fr.uge.plutus.layout.wallet_overview

import fr.uge.plutus.data.model.Wallet

data class WalletOverviewState(
    val wallets: List<Wallet> = emptyList()
)