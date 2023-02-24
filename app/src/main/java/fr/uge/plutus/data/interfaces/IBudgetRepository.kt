package fr.uge.plutus.data.interfaces

import fr.uge.plutus.data.model.Budget
import fr.uge.plutus.data.model.BudgetAndTag
import kotlinx.coroutines.flow.Flow

interface IBudgetRepository {
    fun retrieveAllBudget(): Flow<List<BudgetAndTag>>

    suspend fun createBudget(budget: Budget)

    suspend fun retrieveBudget(id: Int): BudgetAndTag?

    suspend fun updateBudget(budget: Budget): Int

    suspend fun deleteBudget(budget: Budget): Int

}