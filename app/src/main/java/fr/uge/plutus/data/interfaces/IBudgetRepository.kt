package fr.uge.plutus.data.interfaces

import fr.uge.plutus.data.model.Budget
import fr.uge.plutus.data.model.BudgetAndTag
import fr.uge.plutus.data.model.BudgetStatus
import fr.uge.plutus.data.model.Tag
import kotlinx.coroutines.flow.Flow

interface IBudgetRepository {
    fun retrieveAllBudget(): Flow<List<BudgetAndTag>>

    fun retrieveAllBudget(wallet: Int): Flow<List<BudgetAndTag>>

//    fun retrieveAllBudget(wallet: Int, type: Tag.Type): Flow<List<BudgetAndTag>>

    fun retrieveAllBudget(wallet: Int, type: Tag.Type): Flow<List<BudgetStatus>>

    suspend fun createBudget(budget: Budget)

    suspend fun retrieveBudget(id: Long): BudgetAndTag?

    suspend fun updateBudget(budget: Budget): Int

    suspend fun deleteBudget(budget: Budget): Int

}