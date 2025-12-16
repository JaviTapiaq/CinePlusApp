package com.example.cineplus.data.remote.dto
import com.google.gson.annotations.SerializedName


data class MovieDto(
    val id: Int,
    val titulo: String,
    val categoria: String,
    @SerializedName("restriccion_edad") val restriccionEdad: String,
    val duracion: Int,
    val horarios: List<String>,
    val imagen: ImagenDto
)

