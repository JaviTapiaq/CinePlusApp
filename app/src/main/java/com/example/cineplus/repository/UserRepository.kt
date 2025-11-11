package com.example.cineplus.repository

import com.example.cineplus.data.dao.UserDao
import com.example.cineplus.data.entities.UserEntity

class UserRepository(private val userDao: UserDao) {

    suspend fun getUserByUsername(username: String): UserEntity? {
        return userDao.getUserByUsername(username)
    }

    suspend fun insertUser(user: UserEntity) {
        userDao.insertUser(user)
    }

    // Este método puede limpiar sesión o cache si implementas login persistente
    suspend fun clearSession() {
        // Implementar más adelante si usas SharedPreferences o similar
    }
}






