package fr.uge.plutus.data.repository

import fr.uge.plutus.data.interfaces.ITransactionRepository
import fr.uge.plutus.data.model.Transaction
import kotlinx.coroutines.flow.Flow

class TransactionRepository: ITransactionRepository {

    override fun retrieveAllTransaction(): Flow<List<Transaction>> {
        TODO("Not yet implemented")
    }

    override suspend fun createTransaction(transaction: Transaction): Transaction {
        TODO("Not yet implemented")
    }

    override suspend fun retrieveTransaction(id: Int): Transaction? {
        TODO("Not yet implemented")
    }

    override suspend fun updateTransaction(transaction: Transaction): Transaction {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTransaction(transaction: Transaction): Boolean {
        TODO("Not yet implemented")
    }
}