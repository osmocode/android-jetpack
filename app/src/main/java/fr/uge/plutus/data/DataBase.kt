package fr.uge.plutus.data

import androidx.room.Database
import androidx.room.RoomDatabase
import fr.uge.plutus.data.dao.BudgetDao
import fr.uge.plutus.data.dao.TagDao
import fr.uge.plutus.data.dao.TransactionDao
import fr.uge.plutus.data.model.Budget
import fr.uge.plutus.data.model.Tag
import fr.uge.plutus.data.model.Transaction

@Database(
    entities = [Transaction::class, Tag::class, Budget::class],
    version = 1
)
abstract class DataBase : RoomDatabase() {

    abstract fun transactionDao(): TransactionDao

    abstract fun tagDao(): TagDao

    abstract fun budgetDao(): BudgetDao
}