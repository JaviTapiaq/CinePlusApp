package com.example.cineplus.repository


import android.content.Context
import com.example.cineplus.data.local.SessionManager
import okhttp3.Interceptor
import okhttp3.Response
import kotlinx.coroutines.runBlocking
import com.example.cineplus.data.remote.api.ApiService
import com.example.cineplus.data.remote.dto.LoginRequest
import com.example.cineplus.data.remote.dto.RegisterRequest
import com.example.cineplus.data.remote.dto.MeResponse
import com.example.cineplus.data.remote.RetrofitClient



class AuthRepository(context: Context) {

    private val api = RetrofitClient.create(context)
    private val session = SessionManager(context)

    suspend fun login(
        username: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        try {
            val response = api.login(LoginRequest(username, password))
            session.saveAuthToken(response.authToken)
            onSuccess()
        } catch (e: Exception) {
            onError("Credenciales incorrectas")
        }
    }

    suspend fun register(
        username: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        try {
            api.register(RegisterRequest(username, password))
            onSuccess()
        } catch (e: Exception) {
            onError("Error al registrar")
        }
    }

    suspend fun getMyUser(): MeResponse? = try {
        api.getCurrentUser()
    } catch (_: Exception) { null }

    suspend fun logout() {
        session.clearTokens()
    }
}










