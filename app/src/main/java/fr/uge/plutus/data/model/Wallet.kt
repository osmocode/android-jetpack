package fr.uge.plutus.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Wallet(
    @PrimaryKey(autoGenerate = true)
    val walletId: Long?,
    val name: String
)