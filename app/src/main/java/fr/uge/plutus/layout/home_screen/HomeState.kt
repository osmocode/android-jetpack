package fr.uge.plutus.layout.home_screen

import fr.uge.plutus.data.model.Transaction

data class HomeState(
    val transactions: List<Transaction>? = null
)