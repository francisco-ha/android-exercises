package com.example.poo_1.dataStrorePreferences
/*
import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

// El delegado "by preferencesDataStore" se queda a nivel de archivo para ser un Singleton único
//val Context.dataStore by preferencesDataStore(name = "USER_PREFERENCE_NAME")

class DataStorePrefs(private val context: Context) {

    companion object {
        private val NAME_KEY = stringPreferencesKey("name")
        private val VIP_KEY = booleanPreferencesKey("vip")
    }

    // Guardar (Funciones suspend)
    suspend fun saveName(name: String) {
        context.dataStore.edit { preferences ->
            preferences[NAME_KEY] = name
        }
    }

    suspend fun saveVIP(vip: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[VIP_KEY] = vip
        }
    }

    // Leer (Funciones suspend usando .first())
    suspend fun getName(): String {
        return context.dataStore.data.first()[NAME_KEY].orEmpty()
    }

    suspend fun getVIP(): Boolean {
        return context.dataStore.data.first()[VIP_KEY] ?: false
    }

    // Limpiar todo (Equivalente a tu .wipe())
    suspend fun wipe() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}
*/


import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

//val Context.dataStore by preferencesDataStore(name = "USER_PREFERENCE_NAME")

class DataStoreRepository(private val context: Context) {

    /*companion object {
        private val NAME_KEY = stringPreferencesKey("name")
        private val VIP_KEY = booleanPreferencesKey("vip")
    }*/

    val userProfileFlow: Flow<UserProfile> = context.dataStore.data.map { preferences ->
        UserProfile(
            name = preferences[NAME_KEY].orEmpty(),
            isVip = preferences[VIP_KEY] ?: false
        )
    }

    suspend fun saveValues(name: String, checked: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[NAME_KEY] = name
            preferences[VIP_KEY] = checked
        }
    }

    // NUEVO: Verifica de forma rápida si el nombre no está vacío
    suspend fun isUserRegistered(): Boolean {
        val savedName = context.dataStore.data.first()[NAME_KEY].orEmpty()
        return savedName.isNotEmpty()
    }

    // NUEVO: Borra absolutamente todos los datos de este DataStore
    suspend fun clearData() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}
