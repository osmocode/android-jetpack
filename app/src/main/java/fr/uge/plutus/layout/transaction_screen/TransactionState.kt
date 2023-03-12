package fr.uge.plutus.layout.transaction_screen

import fr.uge.plutus.data.model.Price
import fr.uge.plutus.data.model.Tag
import fr.uge.plutus.data.model.Transaction
import fr.uge.plutus.data.model.TransactionWithTags

data class TransactionState(
    val transactionWithTags: TransactionWithTags = TransactionWithTags(
        transaction = Transaction(
            transactionId = null,
            title = "",
            description = "",
            type = "",
            price = Price(
                currency = "$",
                amount = 0.0
            ),
            timestamp = 0.0,
            wallet = -1
        ),
        tags = emptyList()
    ),
    val newTags: List<Tag> = emptyList(),
    val tags: List<Tag> = emptyList(),
    val action: String = "",
    val type: String = "",
    val id: Int = -1
)