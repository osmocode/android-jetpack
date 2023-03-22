package fr.uge.plutus.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import fr.uge.plutus.data.model.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {

    @Query("SELECT * FROM `Transaction`")
    fun retrieveAll(): Flow<List<Transaction>>

    @Query("SELECT * FROM `Transaction` WHERE wallet=:wallet")
    fun retrieveAll(wallet: Int): Flow<List<Transaction>>

    @Query("SELECT * FROM `Transaction` WHERE wallet=:wallet ORDER BY id DESC LIMIT :limit")
    fun retrieveLast(wallet: Int, limit: Int): Flow<List<Transaction>>

    @Query("SELECT * FROM `Transaction` WHERE wallet=:wallet AND timestamp <= :today ORDER BY timestamp DESC")
    fun retrieveAllPast(wallet: Int, today: Long = System.currentTimeMillis()): Flow<List<Transaction>>

    @Query("SELECT * FROM `Transaction` WHERE wallet=:wallet AND timestamp >= :today ORDER BY timestamp")
    fun retrieveAllComing(wallet: Int, today: Long = System.currentTimeMillis()): Flow<List<Transaction>>

    @Query("SELECT * FROM `Transaction` WHERE id=:id")
    suspend fun retrieveById(id: Int): Transaction?

    @Insert
    suspend fun create(transaction: Transaction)

    @Update
    suspend fun update(transaction: Transaction): Int

    @Delete
    suspend fun delete(transaction: Transaction): Int
}