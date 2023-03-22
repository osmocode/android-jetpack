package fr.uge.plutus.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class BudgetAndTag(
    @Embedded
    val budget: Budget,

    @Relation(
        parentColumn = "tagId",
        entityColumn = "tagId"
    )
    val tag: Tag
)
