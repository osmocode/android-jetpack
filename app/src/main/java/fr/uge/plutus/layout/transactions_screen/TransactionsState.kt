package fr.uge.plutus.layout.transactions_screen

import fr.uge.plutus.data.model.Transaction

data class TransactionsState(
    val past: List<Transaction>? = null,
    val coming: List<Transaction>? = null,
)