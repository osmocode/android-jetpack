package fr.uge.plutus.data.interfaces

import fr.uge.plutus.data.model.Tag
import fr.uge.plutus.data.model.Transaction
import fr.uge.plutus.data.model.TransactionWithTags
import kotlinx.coroutines.flow.Flow


interface ITransactionRepository {

    fun retrieveAllTransaction(): Flow<List<Transaction>>

    fun retrieveAllTransaction(wallet: Int): Flow<List<Transaction>>

    fun retrieveLastTransaction(wallet: Int, limit: Int): Flow<List<Transaction>>

    fun retrieveAllPastTransaction(wallet: Int): Flow<List<Transaction>>

    fun retrieveAllComingTransaction(wallet: Int): Flow<List<Transaction>>

    suspend fun createTransaction(transaction: Transaction): Long

    suspend fun retrieveTransaction(id: Int): Transaction?

    suspend fun updateTransaction(transaction: Transaction): Int

    suspend fun deleteTransaction(transaction: Transaction): Int

    suspend fun retrieveTransactionWithTag(id: Int): TransactionWithTags?

    //suspend fun createTransactionWithTags(transactionWithTags: TransactionWithTags)

    suspend fun createTransactionWithTags(transaction: Transaction, ttags: List<Tag>)

//    suspend fun updateTransactionTag(transactionWithTags: TransactionWithTags, previousTags: List<Tag>)

    suspend fun updateTransactionTag(
        transaction: Transaction,
        ttags: List<Tag>,
        previousTags: List<Tag>
    )
}