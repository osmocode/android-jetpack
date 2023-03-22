package fr.uge.plutus.data.model

data class BudgetStatus(
    val budgetId: Long,
    val tagId: Long,
    val label: String,
    val dateStart: Long,
    val dateEnd: Long,
    val currency: String,
    val target: Double,
    val current: Double,
)
