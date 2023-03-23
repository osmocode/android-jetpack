package fr.uge.plutus.data.repository

import fr.uge.plutus.data.dao.TransactionDao
import fr.uge.plutus.data.interfaces.ITransactionRepository
import fr.uge.plutus.data.model.Tag
import fr.uge.plutus.data.model.Transaction
import fr.uge.plutus.data.model.TransactionAndTags
import fr.uge.plutus.data.model.TransactionWithTags
import kotlinx.coroutines.flow.*
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

    override suspend fun updateTransactionWithTag(
        transaction: Transaction,
        ttags: List<Tag>,
        previousTags: List<Tag>
    ) {
        transactionDao.update(transaction)

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

    override suspend fun duplicateTransactionWithTags(walletSrc: Int, walletDest: Int) {
        val transactionsSrc = transactionDao.retrieveAllWithTag(walletSrc)
        //val transactionDest = transactionDao.duplicateTransaction(walletSrc, walletDest)

        transactionsSrc.map { transactionWithTags ->
            TransactionWithTags(
                transaction = transactionWithTags.transaction.copy(walletId = walletDest),
                tags = transactionWithTags.tags
            )
        }.forEach {
            createTransactionWithTags(it.transaction, it.tags)
        }

        /*transactionsSrc.collect{  transactionsWithTags ->
            transactionsWithTags.forEach { transactionWithTags ->
                transactionDao.duplicateTransactionAndTags(
                    transactionSrc = transactionWithTags.transaction.transactionId!!,
                    transactionDest = transactionDest
                )
            }
        }*/
    }

    override suspend fun retrieveTransactionWithTag(id: Long): TransactionWithTags? =
        transactionDao.retrieveWithTagById(id)

    override fun retrieveAllTransactionWithTag(wallet: Int) =
        transactionDao.retrieveAllWithTag(wallet)
}