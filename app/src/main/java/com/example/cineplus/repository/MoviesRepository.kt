package com.example.cineplus.repository

import android.content.Context
import com.example.cineplus.data.remote.MoviesRetrofitClient
import com.example.cineplus.data.remote.dto.MovieDto

class MoviesRepository(context: Context) {

    private val api = MoviesRetrofitClient.create()

    suspend fun getMovies(): List<MovieDto> {
        return api.getMovies()
    }
}
