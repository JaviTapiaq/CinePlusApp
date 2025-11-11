package com.example.cineplus.model

data class User(
    val id: Int = 0,
    val username: String,
    val email: String? = null,
    val subscription: String? = "Gratis"
)
