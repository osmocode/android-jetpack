package fr.uge.plutus.data

import androidx.room.Database
import androidx.room.RoomDatabase
import fr.uge.plutus.data.dao.*
import fr.uge.plutus.data.model.*

@Database(
    entities = [Transaction::class, Tag::class, Budget::class, Wallet::class, TransactionAndTags::class],
    version = 1
)
abstract class DataBase : RoomDatabase() {

    abstract fun transactionDao(): TransactionDao

    abstract fun tagDao(): TagDao

    abstract fun budgetDao(): BudgetDao

    abstract fun walletDao(): WalletDao
}