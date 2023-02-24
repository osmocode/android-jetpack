package fr.uge.plutus.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class BudgetAndTag(
    @Embedded
    val tag: Tag,

    @Relation(
        parentColumn = "id",
        entityColumn = "tag"
    )
    val budget: Budget
)
