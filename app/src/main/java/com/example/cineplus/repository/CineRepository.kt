package com.example.cineplus.repository


import com.example.cineplus.data.remote.ApiService
import com.example.cineplus.data.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CineRepository(private val api: ApiService) {

    suspend fun login(email: String, password: String): Result<Map<String, Any>> {
        return withContext(Dispatchers.IO) {
            try {
                val resp = api.login(mapOf("email" to email, "password" to password))
                if (resp.isSuccessful) Result.success(resp.body()!!)
                else Result.failure(Exception("Login failed: ${resp.code()}"))
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

}