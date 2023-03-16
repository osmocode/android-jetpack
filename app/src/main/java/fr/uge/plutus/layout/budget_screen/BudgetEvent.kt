package fr.uge.plutus.layout.budget_screen

import fr.uge.plutus.data.model.Price
import fr.uge.plutus.data.model.Tag

sealed class BudgetEvent {

    data class BudgetUpdatePrice(val price: Price) : BudgetEvent()

    data class BudgetUpdateTag(val tag: List<Tag>) : BudgetEvent()

    data class BudgetSubmit(val wallet: Int) : BudgetEvent()

    data class BudgetTagCreate(val label: String) : BudgetEvent()
}