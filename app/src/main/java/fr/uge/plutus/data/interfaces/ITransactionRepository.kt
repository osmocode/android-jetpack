package fr.uge.plutus.data.interfaces

import fr.uge.plutus.data.model.Transaction
import kotlinx.coroutines.flow.Flow


interface ITransactionRepository {

    fun retrieveAllTransaction(): Flow<List<Transaction>>

    suspend fun createTransaction(transaction: Transaction)

    suspend fun retrieveTransaction(id: Int): Transaction?

    suspend fun updateTransaction(transaction: Transaction): Int

    suspend fun deleteTransaction(transaction: Transaction): Int
}