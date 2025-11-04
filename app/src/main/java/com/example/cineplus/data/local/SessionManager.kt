package com.example.cineplus.data.local



import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "cineplus_prefs")

class SessionManager(private val context: Context) {
    companion object {
        val KEY_TOKEN = stringPreferencesKey("auth_token")
    }

    suspend fun saveAuthToken(token: String) {
        context.dataStore.edit { prefs ->
            prefs[KEY_TOKEN] = token
        }

        TokenStore.token = token
    }

    suspend fun clearAuthToken() {
        context.dataStore.edit { prefs ->
            prefs.remove(KEY_TOKEN)
        }
        TokenStore.token = null
    }

    fun authTokenFlow(): Flow<String?> {
        return context.dataStore.data.map { prefs ->
            prefs[KEY_TOKEN]
        }
    }
}

