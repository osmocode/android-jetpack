package fr.uge.plutus.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Transaction(

    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val desc: String,
    // val data
    @Embedded
    val price: Price,
    val timestamp: Double
)
