package fr.uge.plutus.storage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.settings: DataStore<Preferences> by preferencesDataStore(name = "settings")

class StorageRepository(
    context: Context
) {
    companion object {
        val DARK = booleanPreferencesKey(name = "dark")
        val WALLET = longPreferencesKey(name = "wallet")
    }

    private val settings = context.settings

    val dark: Flow<Boolean?> = settings.data.map { preferences ->
        preferences[DARK]
    }

    suspend fun dark(value: Boolean?) = settings.edit { preferences ->
        if (value == null) preferences.remove(DARK) else preferences[DARK] = value
    }

    val wallet: Flow<Long?> = settings.data.map { preferences ->
        preferences[WALLET]
    }

    suspend fun wallet(value: Long?) = settings.edit { preferences ->
        if (value == null) preferences.remove(WALLET) else preferences[WALLET] = value
    }

}