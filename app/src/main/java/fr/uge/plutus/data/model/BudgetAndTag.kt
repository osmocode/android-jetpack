package fr.uge.plutus.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class BudgetAndTag(
    @Embedded
    val budget: Budget,

    @Relation(
        parentColumn = "tag",
        entityColumn = "id"
    )
    val tag: Tag
)
