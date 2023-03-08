package fr.uge.plutus.layout.transaction_screen

import fr.uge.plutus.data.model.Price
import fr.uge.plutus.data.model.Tag
import fr.uge.plutus.data.model.Transaction

data class TransactionState(
    val transaction: Transaction = Transaction(
        id = null,
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
    val tags: List<Tag> = emptyList(),
    val action: String = "",
    val type: String = "",
    val id: Int = -1
)