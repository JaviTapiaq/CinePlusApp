package com.example.cineplus.data.local

import android.content.Context
import android.preference.PreferenceManager

fun fetchAuthToken(context: Context): String? {
    val prefs = PreferenceManager.getDefaultSharedPreferences(context)
    return prefs.getString("auth_token", null)
}
