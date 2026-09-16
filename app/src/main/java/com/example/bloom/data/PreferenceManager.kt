package com.example.bloom.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "bloom_prefs")

class PreferenceManager(private val context: Context) {

    companion object {
        val IS_ONBOARDING_COMPLETED = booleanPreferencesKey("is_onboarding_completed")
        val USER_NAME = stringPreferencesKey("user_name")
    }

    val isOnboardingCompleted: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[IS_ONBOARDING_COMPLETED] ?: false
        }

    val userName: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[USER_NAME] ?: "Sofia"
        }

    suspend fun setOnboardingCompleted(completed: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[IS_ONBOARDING_COMPLETED] = completed
        }
    }

    suspend fun setUserName(name: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_NAME] = name
        }
    }
}
