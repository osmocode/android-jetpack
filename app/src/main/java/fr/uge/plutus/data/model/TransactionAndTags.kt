package fr.uge.plutus.data.model

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    primaryKeys = ["transactionId", "tagId"],
    foreignKeys = [
        ForeignKey(
            entity = Transaction::class,
            parentColumns = arrayOf("transactionId"),
            childColumns = arrayOf("transactionId"),
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Tag::class,
            parentColumns = arrayOf("tagId"),
            childColumns = arrayOf("tagId"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class TransactionAndTags(
    val transactionId: Long,
    val tagId: Long
)