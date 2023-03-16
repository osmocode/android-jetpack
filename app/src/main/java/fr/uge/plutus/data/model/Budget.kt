package fr.uge.plutus.data.model

import androidx.room.*

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Tag::class,
            parentColumns = arrayOf("tagId"),
            childColumns = arrayOf("tagId"),
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Wallet::class,
            parentColumns = arrayOf("walletId"),
            childColumns = arrayOf("walletId"),
            onDelete = ForeignKey.CASCADE
        )]
)
data class Budget(
    @PrimaryKey(autoGenerate = true)
    val budgetId: Long?,
    @Embedded
    val targetPrice: Price,
    val dateStart: Long,
    val dateEnd: Long,
    @ColumnInfo(index = false)
    val tagId: Long,
    @ColumnInfo(index = false)
    val walletId: Int
)