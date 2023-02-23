package fr.uge.plutus.layout.transaction_new

import fr.uge.plutus.data.model.Price
import fr.uge.plutus.data.model.Transaction

sealed class TransactionNewEvent {
    data class EditTitle(val title: String) : TransactionNewEvent()
    data class EditDescription(val description: String) : TransactionNewEvent()
    data class EditPrice(val price: Price) : TransactionNewEvent()
    data class SubmitTransaction(val transaction: Transaction) : TransactionNewEvent()
}