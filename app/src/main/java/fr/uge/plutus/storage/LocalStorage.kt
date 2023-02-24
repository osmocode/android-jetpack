package fr.uge.plutus.storage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map


class LocalStorage(
    context: Context
) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("Plutus")
    private val localStorage = context.dataStore

    companion object {
        val DARK = booleanPreferencesKey("dark")
        val WALLET = intPreferencesKey("wallet")
    }

    suspend fun getDark(): Boolean? {
        val storage = localStorage.data.first()
        return storage[DARK]
    }

    fun getDarkAsState() = localStorage.data.map { storage ->
        storage[DARK]
    }

    suspend fun setDark(value: Boolean?) = localStorage.edit { storage ->
        if (value == null) {
            storage.remove(DARK)
        } else {
            storage[DARK] = value
        }
    }

    suspend fun getWallet(): Int? {
        val storage = localStorage.data.first()
        return storage[WALLET]
    }

    fun getWalletAsState() = localStorage.data.map { storage ->
        storage[WALLET] ?: -1
    }

    suspend fun setWallet(value: Int?) = localStorage.edit { storage ->
        if (value == null) {
            storage.remove(WALLET)
        } else {
            storage[WALLET] = value
        }
    }

}