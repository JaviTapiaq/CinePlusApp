package com.example.cineplus.data.remote.dto

data class LoginResponse(
    val nombreUsuario: String,
    val firstName: String,
    val lastName: String,
    val correo: String,
    val accessToken: String
)

