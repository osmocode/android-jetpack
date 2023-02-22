package fr.uge.plutus.layout.transaction_new

import fr.uge.plutus.data.model.Price

data class TransactionNewState(
//        val desc: String = "",
        val price:Price = Price("$", 0.0),
//        val timestamp:Double = System.currentTimeMillis().toDouble()
)
