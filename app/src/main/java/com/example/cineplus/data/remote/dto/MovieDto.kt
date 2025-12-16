package com.example.cineplus.data.remote.dto

data class MovieDto(
    val id: Int,
    val titulo: String,
    val categoria: String,
    val restriccion_edad: String,
    val duracion: Int,
    val horarios: List<String>,
    val imagen: ImagenDto
)
