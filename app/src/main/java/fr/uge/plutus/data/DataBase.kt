package fr.uge.plutus.data

import androidx.room.Database
import androidx.room.RoomDatabase
import fr.uge.plutus.data.dao.TransactionDao
import fr.uge.plutus.data.dao.WalletDao
import fr.uge.plutus.data.model.Transaction
import fr.uge.plutus.data.model.Wallet

@Database(
    entities = [Transaction::class, Wallet::class],
    version = 1
)
abstract class DataBase : RoomDatabase() {

    abstract fun transactionDao(): TransactionDao

    abstract fun walletDao(): WalletDao
}