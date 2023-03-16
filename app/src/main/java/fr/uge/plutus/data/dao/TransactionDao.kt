package fr.uge.plutus.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import fr.uge.plutus.data.model.Transaction
import fr.uge.plutus.data.model.TransactionAndTags
import fr.uge.plutus.data.model.TransactionWithTags
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {

    @Query("SELECT * FROM `Transaction`")
    fun retrieveTransactionWithTags(): Flow<List<TransactionWithTags>>

    @Query("SELECT * FROM `Transaction`")
    fun retrieveAll(): Flow<List<Transaction>>

    @Query("SELECT * FROM `Transaction` WHERE walletId=:wallet")
    fun retrieveAll(wallet: Int): Flow<List<Transaction>>

    @Query("SELECT * FROM `Transaction` WHERE walletId=:wallet ORDER BY transactionId DESC LIMIT :limit")
    fun retrieveLast(wallet: Int, limit: Int): Flow<List<Transaction>>

    @Query("SELECT * FROM `Transaction` WHERE walletId=:wallet ORDER BY timestamp DESC")
    fun retrieveAllPast(wallet: Int): Flow<List<Transaction>>

    @Query("SELECT * FROM `Transaction` WHERE walletId=:wallet ORDER BY timestamp")
    fun retrieveAllComing(wallet: Int): Flow<List<Transaction>>

    @Query("SELECT * FROM `Transaction` WHERE transactionId=:id")
    suspend fun retrieveById(id: Int): Transaction?

    @Insert
    suspend fun create(transaction: Transaction): Long

    @Update
    suspend fun update(transaction: Transaction): Int

    @Delete
    suspend fun delete(transaction: Transaction): Int

    @androidx.room.Transaction
    @Query("SELECT * FROM `Transaction` WHERE transactionId=:id")
    suspend fun retrieveWithTagById(id: Long): TransactionWithTags?

    @Insert
    suspend fun createTransactionAndTags(transactionAndTags: TransactionAndTags)

    @Delete
    suspend fun deleteTransactionAndTags(transactionAndTags: TransactionAndTags): Int


}