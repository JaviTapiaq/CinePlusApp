package com.example.cineplus.data

import android.content.Context
import androidx.room.Room

object DatabaseProvider {

    @Volatile
    private var INSTANCE: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "cineplus_db"
            )
                // 🔄 Borra y recrea la base si cambia el esquema (útil en desarrollo)
                .fallbackToDestructiveMigration()
                // ✅ Crea la instancia una sola vez
                .build()
            INSTANCE = instance
            instance
        }
    }
}



