package com.example.cineplus.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cineplus.repository.MoviesRepository
import com.example.cineplus.data.remote.dto.MovieDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MoviesViewModel(context: Context) : ViewModel() {

    private val repo = MoviesRepository(context)

    private val _movies = MutableStateFlow<List<MovieDto>>(emptyList())
    val movies: StateFlow<List<MovieDto>> = _movies

    private val _search = MutableStateFlow("")
    val search: StateFlow<String> = _search

    fun loadMovies() {
        viewModelScope.launch {
            repo.getMovies().onSuccess { lista ->
                _movies.value = lista
            }.onFailure {
                it.printStackTrace()
            }
        }
    }

    fun updateSearch(text: String) {
        _search.value = text
    }

    fun filteredMovies(): List<MovieDto> {
        val q = search.value.lowercase()
        return movies.value.filter { it.titulo.lowercase().contains(q) }
    }
}
