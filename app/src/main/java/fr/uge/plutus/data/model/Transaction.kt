package fr.uge.plutus.data.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = Wallet::class,
        parentColumns = arrayOf("walletId"),
        childColumns = arrayOf("wallet"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class Transaction(

    @PrimaryKey(autoGenerate = true)
    val transactionId: Long?,
    val title: String,
    val description: String,
    val type: String,
    @Embedded
    val price: Price,
    val timestamp: Double,
    @ColumnInfo(index = false)
    val wallet: Int
) {
    enum class Type {
        CREDIT,
        DEBIT,
        TRANSFER;

        override fun toString(): String {
            return this.name
        }
    }
}
