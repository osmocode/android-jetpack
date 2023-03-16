package fr.uge.plutus.layout.home_screen

import fr.uge.plutus.data.model.BudgetStatus
import fr.uge.plutus.data.model.Transaction

data class HomeState(
    val transactions: List<Transaction>? = null,

    val budgetsIncoming: List<BudgetStatus> = emptyList(),
    val budgetsExpend: List<BudgetStatus> = emptyList(),
    val budgetsTransfer: List<BudgetStatus> = emptyList()
)