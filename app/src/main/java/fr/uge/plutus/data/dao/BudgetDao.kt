package fr.uge.plutus.data.dao

import androidx.room.*
import androidx.room.Transaction
import fr.uge.plutus.data.model.*
import kotlinx.coroutines.flow.Flow

@Dao
interface BudgetDao {

    @Transaction
    @Query("SELECT * FROM `Budget`")
    fun retrieveAll(): Flow<List<BudgetAndTag>>

    @Transaction
    @Query("SELECT * FROM `Budget` WHERE walletId=:wallet")
    fun retrieveAll(wallet: Long): Flow<List<BudgetAndTag>>

    @Transaction
    @Query("SELECT * FROM `Budget` NATURAL JOIN `Tag` WHERE walletId=:wallet AND type=:type")
    fun retrieveAll(wallet: Long, type: Tag.Type): Flow<List<BudgetAndTag>>

    @Transaction
    @Query(
        """SELECT budgetId, Budget.tagId, label, dateStart, dateEnd, Budget.currency, Budget.amount as target, SUM(IFNULL(`Transaction`.amount, 0)) as current
            FROM Budget
            NATURAL LEFT JOIN Tag
            NATURAL LEFT JOIN TransactionAndTags
            LEFT JOIN `Transaction` 
            ON `Transaction`.transactionId = TransactionAndTags.transactionId
            AND timestamp BETWEEN dateStart AND dateEnd 
            WHERE Budget.walletId=:wallet AND Tag.type=:type
            GROUP BY Budget.budgetId"""
    )
    fun retrieveAllBudget(wallet: Long, type: Tag.Type): Flow<List<BudgetStatus>>

    @Transaction
    @Query("SELECT * FROM `Budget` WHERE budgetId=:id")
    suspend fun retrieveById(id: Long): BudgetAndTag?

    @Insert
    suspend fun create(budget: Budget)

    @Update
    suspend fun update(budget: Budget): Int

    @Delete
    suspend fun delete(budget: Budget): Int

}