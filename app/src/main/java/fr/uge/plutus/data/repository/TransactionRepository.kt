package fr.uge.plutus.data.repository

import fr.uge.plutus.data.dao.TransactionDao
import fr.uge.plutus.data.interfaces.ITransactionRepository
import fr.uge.plutus.data.model.Transaction
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TransactionRepository @Inject constructor(
    private val transactionDao: TransactionDao
) : ITransactionRepository {
    override fun retrieveAllTransaction(): Flow<List<Transaction>> = transactionDao.retrieveAll()

    override suspend fun createTransaction(transaction: Transaction) {
        transactionDao.create(transaction)
    }

    override suspend fun retrieveTransaction(id: Int): Transaction? =
        transactionDao.retrieveById(id)

    override suspend fun updateTransaction(transaction: Transaction): Int =
        transactionDao.update(transaction)

    override suspend fun deleteTransaction(transaction: Transaction): Int =
        transactionDao.delete(transaction)
}