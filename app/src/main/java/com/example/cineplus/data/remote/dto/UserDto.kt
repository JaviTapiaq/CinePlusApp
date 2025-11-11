package com.example.cineplus.data.remote.dto

import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName("id")
    val id: Int,

    @SerializedName("nombreUsuario")
    val nombreUsuario: String,

    @SerializedName("correo")
    val correo: String,

    @SerializedName("firstName")
    val nombre: String,

    @SerializedName("lastName")
    val apellido: String,

    @SerializedName("image")
    val imagen: String? = null  // URL de imagen de perfil (opcional)
)