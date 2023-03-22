package fr.uge.plutus.layout.budget_screen

import fr.uge.plutus.data.model.Budget
import fr.uge.plutus.data.model.Price
import fr.uge.plutus.data.model.Tag

data class BudgetState(
    val budget: Budget = Budget(
        budgetId = null,
        targetPrice = Price(
            currency = "$",
            amount = 0.0
        ),
        dateStart = 0L,
        dateEnd = 0L,
        tagId = -1,
        walletId = -1
    ),
    val tags: List<Tag> = emptyList(),
    val newTag: List<Tag> = emptyList(),
    val type: String = "",
    val wallet: Int = -1
)
