package com.example.cineplus.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    // 🔹 Email o nombre de usuario (clave de login)
    val username: String,

    // 🔹 Contraseña local (puede quedar vacía si viene del backend)
    val password: String,

    // 🔹 Nombre opcional del usuario (desde Xano)
    val name: String? = null,

    // 🔹 Token JWT devuelto por Xano tras login/signup
    val token: String? = null,

    // 🔹 Imagen de perfil almacenada localmente (URI de cámara/galería)
    val profileImageUri: String? = null
)

