package com.example.hairbook.feature.auth.domain.repository

import com.example.hairbook.feature.auth.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun observeCurrentUser(): Flow<User?>
    suspend fun signIn(id: String, displayName: String, email: String?)
    suspend fun signOut()
}
