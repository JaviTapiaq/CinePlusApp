package com.example.cineplus.data.remote


import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @POST("auth/login")
    suspend fun login(@Body body: Map<String, String>): Response<Map<String, Any>>
}