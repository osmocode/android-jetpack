package fr.uge.plutus.layout.transaction_overview

import fr.uge.plutus.data.model.Transaction

data class TransactionOverviewState(
    val transactions: List<Transaction> = emptyList()
)