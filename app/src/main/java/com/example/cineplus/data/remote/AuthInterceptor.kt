package com.example.cineplus.data.remote

import com.example.cineplus.data.local.SessionManager
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val sessionManager: SessionManager
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        //Recupera token usando runBlocking
        val token = runBlocking {
            sessionManager.getAuthToken()
        }

        //Si no hay token, continua con petición original
        if (token.isNullOrEmpty()) {
            return chain.proceed(originalRequest)
        }

        //Crea nueva petición CON el token
        val authenticatedRequest = originalRequest.newBuilder()
            .header("Authorization", "Bearer $token")
            .build()

        //Continua con la petición autenticada
        return chain.proceed(authenticatedRequest)
    }
}