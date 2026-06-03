package com.example.hairbook.feature.auth.data.mapper

import com.example.hairbook.core.database.entity.UserEntity
import com.example.hairbook.feature.auth.domain.model.User

fun UserEntity.toDomain(): User = User(
    id = id,
    displayName = displayName,
    email = email
)

fun User.toEntity(): UserEntity = UserEntity(
    id = id,
    displayName = displayName,
    email = email
)
