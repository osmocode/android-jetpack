package fr.uge.plutus.data

import android.content.Context
import android.util.Log
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

        //@Volatile // To make sure that visible for other thread immediately
        private var INSTANCE: DataBase? = null

        fun getInstance(context: Context): DataBase {
            Log.println(Log.ASSERT, "BDD","context= $context")
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room
                        .databaseBuilder(context/*.applicationContext*/, DataBase::class.java, "plutus.db")
                        .build()
                    INSTANCE = instance
//                    Log.println(Log.ASSERT, "BDD","context= $context")
                }
                return instance
            }
        }
    }
}