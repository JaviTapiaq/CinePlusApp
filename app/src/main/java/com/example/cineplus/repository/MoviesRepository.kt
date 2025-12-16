package com.example.cineplus.repository

import android.content.Context
import com.example.cineplus.data.remote.RetrofitClient
import com.example.cineplus.data.remote.api.ApiService
import com.example.cineplus.data.remote.dto.MovieDto

class MoviesRepository(context: Context) {

    private val api: ApiService = RetrofitClient.create(context)

    suspend fun getMovies(): List<MovieDto> {
        return api.getMovies()
    }
}
