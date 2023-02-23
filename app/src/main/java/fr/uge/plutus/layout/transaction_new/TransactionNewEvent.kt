package fr.uge.plutus.layout.transaction_new

import fr.uge.plutus.data.model.Price
import fr.uge.plutus.data.model.Transaction

sealed class TransactionNewEvent {
    data class EditTitle(val transaction: Transaction) : TransactionNewEvent()
    data class EditDescription(val transaction: Transaction) : TransactionNewEvent()
    data class EditPrice(val price: Price) : TransactionNewEvent()
    data class EditDate(val transaction: Transaction) : TransactionNewEvent()
    data class EditTags(val transaction: Transaction) : TransactionNewEvent()
    data class EditLocation(val transaction: Transaction) : TransactionNewEvent()
}