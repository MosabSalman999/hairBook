package com.example.hairbook.feature.profile.data.mapper

import com.example.hairbook.core.database.entity.UserEntity
import com.example.hairbook.feature.profile.domain.model.Profile

fun UserEntity.toProfile(): Profile = Profile(
    id = id,
    displayName = displayName,
    email = email
)

fun Profile.toEntity(): UserEntity = UserEntity(
    id = id,
    displayName = displayName,
    createdAt = System.currentTimeMillis(),
    email = email
)
