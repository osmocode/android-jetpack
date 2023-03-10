package fr.uge.plutus.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Tag::class,
            parentColumns = arrayOf("tagId"),
            childColumns = arrayOf("tag"),
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Wallet::class,
            parentColumns = arrayOf("walletId"),
            childColumns = arrayOf("wallet"),
            onDelete = ForeignKey.CASCADE
        )]
)
data class Budget(
    @PrimaryKey(autoGenerate = true)
    val budgetId: Long?,
    val targetPrice: Double,
    val dateStart: Double,
    val dateEnd: Double,
    @ColumnInfo(index = false)
    val tag: Int,
    @ColumnInfo(index = false)
    val wallet: Int
)