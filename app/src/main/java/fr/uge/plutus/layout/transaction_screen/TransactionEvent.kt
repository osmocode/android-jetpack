package fr.uge.plutus.layout.transaction_screen

import fr.uge.plutus.data.model.Price

sealed class TransactionEvent {
    data class TransactionUpdatePrice(val price: Price) : TransactionEvent()
    data class TransactionUpdateDesc(val title: String, val desc: String) : TransactionEvent()
    data class TransactionSubmit(val wallet: Int) : TransactionEvent()
    data class TransactionTagCreate(val name: String) : TransactionEvent()
}