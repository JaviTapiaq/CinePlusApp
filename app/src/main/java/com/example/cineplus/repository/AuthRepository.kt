package com.example.cineplus.repository

import com.example.cineplus.data.dao.UserDao
import com.example.cineplus.data.entities.UserEntity

class AuthRepository(private val userDao: UserDao) {

    suspend fun login(username: String, password: String): Boolean {
        val user = userDao.getUserByUsername(username)
        return user?.password == password
    }

    suspend fun register(username: String, password: String) {
        val newUser = UserEntity(username = username, password = password)
        userDao.insertUser(newUser)
    }
}












