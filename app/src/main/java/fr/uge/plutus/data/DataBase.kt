package fr.uge.plutus.data

import androidx.room.Database
import androidx.room.RoomDatabase
import fr.uge.plutus.data.dao.TransactionDao
import fr.uge.plutus.data.model.Transaction

@Database(
    entities = [Transaction::class],
    version = 1
)
abstract class DataBase : RoomDatabase() {

    abstract fun transactionDao(): TransactionDao
}