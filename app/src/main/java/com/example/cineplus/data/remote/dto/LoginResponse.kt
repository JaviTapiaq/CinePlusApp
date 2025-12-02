package com.example.cineplus.data.remote.dto

import com.google.gson.annotations.SerializedName

/**
 * DTO para la respuesta de login
 * Datos que se RECIBEN del servidor tras login exitoso
 */

data class LoginResponse(
    @SerializedName("authToken")
    val authToken: String
)