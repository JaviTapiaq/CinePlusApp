package com.example.cineplus.data.remote.api

import com.example.cineplus.data.remote.dto.LoginRequest
import com.example.cineplus.data.remote.dto.LoginResponse
import com.example.cineplus.data.remote.dto.MeResponse
import com.example.cineplus.data.remote.dto.MovieDto
import com.example.cineplus.data.remote.dto.RegisterRequest
import com.example.cineplus.data.remote.dto.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    // LOGIN
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    // SIGNUO / REGISTRO
    @POST("auth/signup")
    suspend fun register(@Body request: RegisterRequest): RegisterResponse
    // (Xano también devuelve token al registrarse)

    // GET USER (requiere token)
    @GET("auth/me")
    suspend fun getCurrentUser(): MeResponse

}


