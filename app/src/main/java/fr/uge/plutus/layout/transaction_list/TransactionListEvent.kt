package fr.uge.plutus.layout.transaction_list

import fr.uge.plutus.data.model.Transaction

sealed class TransactionListEvent {
    data class AddNewTransaction(val transaction: Transaction) : TransactionListEvent()
    data class SelectTransaction(val transaction: Transaction) : TransactionListEvent()
}
