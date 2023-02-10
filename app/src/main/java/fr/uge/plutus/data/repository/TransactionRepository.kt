package fr.uge.plutus.data.repository

import android.app.Application
import android.content.Context
import fr.uge.plutus.data.DataBase
import fr.uge.plutus.data.dao.TransactionDao
import fr.uge.plutus.data.interfaces.ITransactionRepository
import fr.uge.plutus.data.model.Transaction
import kotlinx.coroutines.flow.Flow

class TransactionRepository(
    private val transactionDao: TransactionDao
) : ITransactionRepository {
    override fun retrieveAllTransaction(): Flow<List<Transaction>> = transactionDao.retrieveAll()

    override suspend fun createTransaction(transaction: Transaction) =
        transactionDao.create(transaction)

    override suspend fun retrieveTransaction(id: Int): Transaction? =
        transactionDao.retrieveById(id)

    override suspend fun updateTransaction(transaction: Transaction): Int =
        transactionDao.update(transaction)

    override suspend fun deleteTransaction(transaction: Transaction): Int =
        transactionDao.delete(transaction)


    companion object {
        fun build(context: Context): TransactionRepository {
            val transactionDao = DataBase.getInstance(context).transactionDao()
            return TransactionRepository(transactionDao)
        }
    }
}