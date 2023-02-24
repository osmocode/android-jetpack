package fr.uge.plutus.storage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

class LocalDateStore(
    context: Context
) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("Plutus")
    private val dataStore = context.dataStore

    companion object {
        val DARK = booleanPreferencesKey("dark")
        val WALLET = intPreferencesKey("wallet")
    }

    fun getDark() = dataStore.data.map { it[DARK] }

    fun getWallet() = dataStore.data.map { it[WALLET] }

    suspend fun setDark(value: Boolean?) = dataStore.edit { storage ->
        if (value == null) storage.remove(DARK) else storage[DARK] = value
    }

    suspend fun setWallet(value: Int?) = dataStore.edit { storage ->
        if (value == null) storage.remove(WALLET) else storage[WALLET] = value
    }

}