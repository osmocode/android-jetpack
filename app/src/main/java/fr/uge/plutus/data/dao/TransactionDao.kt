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

    @Query("SELECT * FROM `Transaction` WHERE id=:id")
    suspend fun retrieveById(id: Int): Transaction?

    @Insert
    suspend fun create(transaction: Transaction)

    @Update
    suspend fun update(transaction: Transaction): Int

    @Delete
    suspend fun delete(transaction: Transaction): Int
}