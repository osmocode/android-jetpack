package fr.uge.plutus.data.model

import androidx.room.Entity

@Entity(primaryKeys = ["transactionId", "tagId"])
data class TransactionAndTags(
    val transactionId: Long,
    val tagId: Long
)