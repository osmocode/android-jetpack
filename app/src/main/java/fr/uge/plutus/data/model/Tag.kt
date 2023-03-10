package fr.uge.plutus.data.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["label", "type"], unique = true)])
data class Tag(

    @PrimaryKey(autoGenerate = true)
    val tagId: Long?,
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