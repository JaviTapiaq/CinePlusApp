package com.example.cineplus.repository

import android.content.Context
import com.example.cineplus.data.remote.RetrofitClient
import com.example.cineplus.data.remote.api.ApiService
import com.example.cineplus.data.remote.dto.MovieDto

class MovieRepository(context: Context) {

    private val api = RetrofitClient
        .create(context)
        .create(ApiService::class.java)

    suspend fun getMovies(): Result<List<MovieDto>> {
        return try {
            Result.success(api.getMovies())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}