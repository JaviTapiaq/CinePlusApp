package com.example.cineplus.data.remote

import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Ajusta esta URL con la de tu API Xano:
private const val BASE_URL = "https://tu-endpoint.xano.io/api:" // cambia por tu endpoint real

data class LoginRequest(
    val email: String,
    val password: String
)

data class LoginResponse(
    val authToken: String?,
    val message: String?
)

interface ApiService {
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse
}

object RetrofitInstance {
    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}




