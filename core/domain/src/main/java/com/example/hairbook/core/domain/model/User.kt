package com.example.hairbook.core.domain.model

data class User(
    val id: String,
    val displayName: String,
    val createdAt: Long,
    val email: String? = null,
    val role: UserRole = UserRole.USER,
    val isGuest: Boolean = false,
)
