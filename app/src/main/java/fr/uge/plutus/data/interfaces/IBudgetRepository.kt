package fr.uge.plutus.data.interfaces

import fr.uge.plutus.data.model.Budget
import fr.uge.plutus.data.model.BudgetAndTag
import kotlinx.coroutines.flow.Flow

interface IBudgetRepository {
    fun retrieveAllBudget(): Flow<List<BudgetAndTag>>

    fun retrieveAllBudget(wallet: Int): Flow<List<BudgetAndTag>>

    suspend fun createBudget(budget: Budget)

    suspend fun retrieveBudget(id: Long): BudgetAndTag?

    suspend fun updateBudget(budget: Budget): Int

    suspend fun deleteBudget(budget: Budget): Int

}