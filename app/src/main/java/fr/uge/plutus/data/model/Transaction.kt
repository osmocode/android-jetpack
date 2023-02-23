package fr.uge.plutus.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Transaction(

    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val title: String,
    val description: String,
    @Embedded
    val price: Price,
    val timestamp: Double
)
