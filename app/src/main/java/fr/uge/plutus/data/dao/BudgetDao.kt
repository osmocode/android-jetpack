package fr.uge.plutus.data.dao

import androidx.room.*
import fr.uge.plutus.data.model.Budget
import fr.uge.plutus.data.model.BudgetAndTag
import kotlinx.coroutines.flow.Flow

@Dao
interface BudgetDao {

    @Transaction
    @Query("SELECT * FROM `Budget`")
    fun retrieveAll(): Flow<List<BudgetAndTag>>

    @Transaction
    @Query("SELECT * FROM `Budget` WHERE id=:id")
    suspend fun retrieveById(id: Int): BudgetAndTag?

    @Insert
    suspend fun create(budget: Budget)

    @Update
    suspend fun update(budget: Budget): Int

    @Delete
    suspend fun delete(budget: Budget): Int

}