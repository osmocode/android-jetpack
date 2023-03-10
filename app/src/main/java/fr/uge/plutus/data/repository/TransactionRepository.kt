package fr.uge.plutus.data.repository

import fr.uge.plutus.data.dao.TransactionDao
import fr.uge.plutus.data.interfaces.ITransactionRepository
import fr.uge.plutus.data.model.Tag
import fr.uge.plutus.data.model.Transaction
import fr.uge.plutus.data.model.TransactionAndTags
import fr.uge.plutus.data.model.TransactionWithTags
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TransactionRepository @Inject constructor(
    private val transactionDao: TransactionDao,
) : ITransactionRepository {
    override fun retrieveAllTransaction(): Flow<List<Transaction>> = transactionDao.retrieveAll()

    override fun retrieveAllTransaction(wallet: Int): Flow<List<Transaction>> =
        transactionDao.retrieveAll(wallet)

    override fun retrieveLastTransaction(wallet: Int, limit: Int): Flow<List<Transaction>> =
        transactionDao.retrieveLast(wallet, limit)

    override fun retrieveAllPastTransaction(wallet: Int): Flow<List<Transaction>> =
        transactionDao.retrieveAllPast(wallet)

    override fun retrieveAllComingTransaction(wallet: Int): Flow<List<Transaction>> =
        transactionDao.retrieveAllComing(wallet)

    override suspend fun createTransaction(transaction: Transaction): Long =
        transactionDao.create(transaction)

    override suspend fun retrieveTransaction(id: Int): Transaction? =
        transactionDao.retrieveById(id)


    override suspend fun updateTransaction(transaction: Transaction): Int =
        transactionDao.update(transaction)

    override suspend fun deleteTransaction(transaction: Transaction): Int =
        transactionDao.delete(transaction)


//    override suspend fun createTransactionAndTags(transactionAndTags: TransactionAndTags) =
//        transactionDao.createTransactionAndTags(transactionAndTags)

    /*override suspend fun createTransactionWithTags(transactionWithTags: TransactionWithTags) {
        val id = transactionDao.create(transactionWithTags.transaction)

        transactionWithTags.tags.forEach { tag ->
            transactionDao.createTransactionAndTags(
                TransactionAndTags(
                    transactionId = id,
                    tagId = tag.tagId!!
                )
            )
        }
    }*/

    override suspend fun createTransactionWithTags(transaction: Transaction, ttags: List<Tag>) {
        val id = transactionDao.create(transaction)

        ttags.forEach { tag ->
            transactionDao.createTransactionAndTags(
                TransactionAndTags(
                    transactionId = id,
                    tagId = tag.tagId!!
                )
            )
        }
    }


    /* override suspend fun updateTransactionTag(
         transactionWithTags: TransactionWithTags,
         previousTags: List<Tag>
     ) {
         // new tag list to previous tag list --> INSERT
         transactionWithTags.tags.forEach {
             if (!previousTags.contains(it))
                 transactionDao.createTransactionAndTags(
                     TransactionAndTags(
                         transactionId = transactionWithTags.transaction.transactionId!!,
                         tagId = it.tagId!!
                     )
                 )
         }

         // previous tag list to new tag list --> DELETE
         previousTags.forEach {
             if (!transactionWithTags.tags.contains(it))
                 transactionDao.deleteTransactionAndTags(
                     TransactionAndTags(
                         transactionId = transactionWithTags.transaction.transactionId!!,
                         tagId = it.tagId!!
                     )
                 )
         }
     }*/


    override suspend fun updateTransactionTag(
        transaction: Transaction,
        ttags: List<Tag>,
        previousTags: List<Tag>
    ) {
        // new tag list to previous tag list --> INSERT
        ttags.forEach {
            if (!previousTags.contains(it))
                transactionDao.createTransactionAndTags(
                    TransactionAndTags(
                        transactionId = transaction.transactionId!!,
                        tagId = it.tagId!!
                    )
                )
        }

        // previous tag list to new tag list --> DELETE
        previousTags.forEach {
            if (!ttags.contains(it))
                transactionDao.deleteTransactionAndTags(
                    TransactionAndTags(
                        transactionId = transaction.transactionId!!,
                        tagId = it.tagId!!
                    )
                )
        }
    }


    override suspend fun retrieveTransactionWithTag(id: Int): TransactionWithTags? =
        transactionDao.retrieveWithTagById(id)
}