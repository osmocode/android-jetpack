package fr.uge.plutus.layout.transactions_screen

sealed class TransactionsEvent {

    data class TransactionsDelete(val id: Long) : TransactionsEvent()

}