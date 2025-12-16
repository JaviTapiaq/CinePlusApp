package com.example.cineplus.data.remote.api

import com.example.cineplus.data.remote.dto.MovieDto
import retrofit2.http.GET

interface MoviesApiService {

    @GET("movies")
    suspend fun getMovies(): List<MovieDto>
}