package fr.uge.plutus.data


import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map


class DataStorage(
    context: Context
) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("Plutus")
    private val localStorage = context.dataStore

    companion object {
        val DARK = booleanPreferencesKey("theme")
        val WALLET = intPreferencesKey("wallet")
    }

    fun getTheme() = localStorage.data.map { storage ->
        storage[DARK]
    }

    suspend fun setTheme(value: Boolean) = localStorage.edit { storage ->
        storage[DARK] = value
    }

    fun getWallet() = localStorage.data.map { storage ->
        storage[WALLET] ?: -1
    }

    suspend fun setWallet(value: Int) = localStorage.edit { storage ->
        storage[WALLET] = value
    }

}

class DataStorageProvider() {

    companion object {
        @Volatile
        private var INSTANCE: DataStorage? = null

        fun getInstance(context: Context): DataStorage = INSTANCE ?: synchronized(this) {
            val instance = DataStorage(context)
            INSTANCE = instance
            return instance
        }
    }

}
