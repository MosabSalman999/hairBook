package com.example.hairbook.core.domain.repository

import com.example.hairbook.core.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun observeCurrentUser(): Flow<User?>

    suspend fun saveUser(user: User)
    suspend fun clearCurrentUser()
}
