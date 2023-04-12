package com.example.ta1app.ui.settings.language


import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LanguagePreferences private constructor(private val dataStore : DataStore<Preferences>){

    private val LANGUAGE_KEY = booleanPreferencesKey("language_setting")

    fun getLanguageSettings(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[LANGUAGE_KEY] ?: false
        }
    }

    suspend fun saveLanguageSetting(language: Boolean) {
        dataStore.edit { preferences ->
            preferences[LANGUAGE_KEY] = language
        }
    }


    companion object {
        @Volatile
        private var INSTANCE: LanguagePreferences? = null

        fun getInstance(dataStore: DataStore<Preferences>): LanguagePreferences {
            return INSTANCE ?: synchronized(this) {
                val instance = LanguagePreferences(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}