package fr.uge.plutus.layout.transaction_new

import fr.uge.plutus.data.model.Price
import fr.uge.plutus.data.model.Transaction

data class TransactionNewState(
    val title: String = "Title",
    val description: String = "Add a description",
    val price: Price = Price("$", 0.0),
    val type: String = Transaction.Type.CREDIT.toString()
)
