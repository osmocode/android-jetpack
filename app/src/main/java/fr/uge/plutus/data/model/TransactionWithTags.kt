package fr.uge.plutus.data.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class TransactionWithTags(
    @Embedded
    val transaction: Transaction,

    @Relation(
        parentColumn = "transactionId",
        entityColumn = "tagId",
        associateBy = Junction(TransactionAndTags::class)
    )
    val tags: List<Tag>
)
