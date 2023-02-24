package fr.uge.plutus.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class TagType {
    CREDIT,
    DEBIT,
    TRANSFER
}

@Entity
data class Tag(

    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val label: String,
    val type: String
)