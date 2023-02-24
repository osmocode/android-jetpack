package fr.uge.plutus.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Tag(

    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val label: String,
    val type: String
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