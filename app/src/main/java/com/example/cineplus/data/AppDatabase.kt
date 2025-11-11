package com.example.cineplus.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cineplus.data.dao.UserDao
import com.example.cineplus.data.entities.UserEntity

// ⚠️ Aumentamos la versión de la base de datos de 1 → 2
@Database(entities = [UserEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}






