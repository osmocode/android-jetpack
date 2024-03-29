package fr.uge.plutus.data.dao

import androidx.room.*
import fr.uge.plutus.data.model.Wallet
import kotlinx.coroutines.flow.Flow

@Dao
interface WalletDao {

    @Query("SELECT * FROM `Wallet`")
    fun retrieveAll(): Flow<List<Wallet>>

    @Query("SELECT * FROM `Wallet` WHERE walletId=:id")
    suspend fun retrieveById(id: Long): Wallet?

    @Insert
    suspend fun create(wallet: Wallet)

    @Update
    suspend fun update(wallet: Wallet): Int

    @Delete
    suspend fun delete(wallet: Wallet): Int
}