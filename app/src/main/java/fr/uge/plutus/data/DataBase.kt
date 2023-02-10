package fr.uge.plutus.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import fr.uge.plutus.data.dao.TransactionDao
import fr.uge.plutus.data.model.Transaction

@Database(
    entities = [Transaction::class],
    version = 1
)
abstract class DataBase : RoomDatabase() {

    abstract fun transactionDao(): TransactionDao

    companion object {

        // @Volatile
        // To use if check was made outside of synchronized
        private var INSTANCE: DataBase? = null

        fun getInstance(context: Context): DataBase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room
                        .databaseBuilder(context, DataBase::class.java, "plutus.db")
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}