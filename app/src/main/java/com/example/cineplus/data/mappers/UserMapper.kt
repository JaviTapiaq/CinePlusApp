package com.example.cineplus.data.mappers

import com.example.cineplus.data.entities.UserEntity
import com.example.cineplus.data.remote.dto.MeResponse

fun MeResponse.toEntity(): UserEntity {
    return UserEntity(
        id = this.id,
        username = this.username
    )
}