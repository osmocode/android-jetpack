package fr.uge.plutus.layout.budget_screen

import fr.uge.plutus.data.model.Budget
import fr.uge.plutus.data.model.Price
import fr.uge.plutus.data.model.Tag
import java.util.*

data class BudgetState(
    val budget: Budget = Budget(
        budgetId = null,
        targetPrice = Price(
            currency = "$",
            amount = 0.0
        ),
        dateStart = Calendar.getInstance().timeInMillis,
        dateEnd = Calendar.getInstance().timeInMillis,
        tagId = -1,
        walletId = -1
    ),
    val tags: List<Tag> = emptyList(),
    val newTag: List<Tag> = emptyList(),
    val type: String = "",
    val wallet: Int = -1
)
