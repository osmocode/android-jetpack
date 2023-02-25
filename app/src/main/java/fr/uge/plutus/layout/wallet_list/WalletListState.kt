package fr.uge.plutus.layout.wallet_list

import fr.uge.plutus.data.model.Wallet

data class WalletListState(
    val wallets: List<Wallet> = emptyList()
)