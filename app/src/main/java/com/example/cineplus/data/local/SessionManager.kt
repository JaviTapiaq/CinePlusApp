package com.example.cineplus.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import java.io.IOException

private val Context.dataStore by preferencesDataStore(name = "session_prefs")

class SessionManager(private val context: Context) {
    companion object {
        private val KEY_AUTH_TOKEN = stringPreferencesKey("auth_token")
        private val KEY_REFRESH_TOKEN = stringPreferencesKey("refresh_token")
        private val KEY_USERS_JSON = stringPreferencesKey("registered_users_json")
        private val gson by lazy { Gson() }
    }

    //accessTokenFlow
    val accessTokenFlow: Flow<String?> =
        context.dataStore.data
            .catch { e ->
                if (e is IOException) {
                    emit(emptyPreferences())
                } else throw e
            }
            .map { it[KEY_AUTH_TOKEN] }
            .distinctUntilChanged()

    val isLoggedInFlow = accessTokenFlow.map { !it.isNullOrBlank() }

    suspend fun saveAuthToken(token: String) {
        context.dataStore.edit { it[KEY_AUTH_TOKEN] = token }
    }
    suspend fun getAuthToken(): String? =
        context.dataStore.data.map { it[KEY_AUTH_TOKEN] }.first()

    suspend fun saveRefreshToken(token: String?) {
        context.dataStore.edit { prefs ->
            if (token == null) prefs.remove(KEY_REFRESH_TOKEN)
            else prefs[KEY_REFRESH_TOKEN] = token
        }
    }
    suspend fun getRefreshToken(): String? =
        context.dataStore.data.map { it[KEY_REFRESH_TOKEN] }.first()

    suspend fun saveTokens(access: String, refresh: String?) {
        context.dataStore.edit { prefs ->
            prefs[KEY_AUTH_TOKEN] = access
            if (refresh == null) prefs.remove(KEY_REFRESH_TOKEN)
            else prefs[KEY_REFRESH_TOKEN] = refresh
        }
    }

    suspend fun clearTokens() {
        context.dataStore.edit {
            it.remove(KEY_AUTH_TOKEN)
            it.remove(KEY_REFRESH_TOKEN)
        }
    }
}
