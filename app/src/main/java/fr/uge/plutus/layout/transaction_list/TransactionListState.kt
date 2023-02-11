package fr.uge.plutus.layout.transaction_list

import fr.uge.plutus.data.model.Transaction

data class TransactionListState(
    val transactions : List<Transaction> = emptyList(),
    //val transactionsByMonth : Map<Date, List<Transaction>> = emptyMap(),
    //val transactionsByMonth : Map<Date?, MutableList<Transaction?>> = emptyMap()
)
