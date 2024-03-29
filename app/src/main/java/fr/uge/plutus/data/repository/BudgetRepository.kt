package fr.uge.plutus.data.repository

import fr.uge.plutus.data.dao.BudgetDao
import fr.uge.plutus.data.interfaces.IBudgetRepository
import fr.uge.plutus.data.model.Budget
import fr.uge.plutus.data.model.BudgetAndTag
import fr.uge.plutus.data.model.BudgetStatus
import fr.uge.plutus.data.model.Tag
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BudgetRepository @Inject constructor(
    private val budgetDao: BudgetDao
) : IBudgetRepository {
    override fun retrieveAllBudget(): Flow<List<BudgetAndTag>> = budgetDao.retrieveAll()

    override fun retrieveAllBudget(wallet: Long): Flow<List<BudgetAndTag>> =
        budgetDao.retrieveAll(wallet)

    /* override fun retrieveAllBudget(wallet: Int, type: Tag.Type): Flow<List<BudgetAndTag>> =
         budgetDao.retrieveAll(
             wallet = wallet,
             type = type
         )*/

    override fun retrieveAllBudget(wallet: Long, type: Tag.Type): Flow<List<BudgetStatus>> =
        budgetDao.retrieveAllBudget(
            wallet = wallet,
            type = type
        )

    override suspend fun createBudget(budget: Budget) = budgetDao.create(budget)

    override suspend fun retrieveBudget(id: Long) = budgetDao.retrieveById(id)

    override suspend fun updateBudget(budget: Budget) = budgetDao.update(budget)

    override suspend fun deleteBudget(budget: Budget) = budgetDao.delete(budget)
}