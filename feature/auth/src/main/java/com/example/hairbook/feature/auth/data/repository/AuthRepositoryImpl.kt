package com.example.hairbook.feature.auth.data.repository

import com.example.hairbook.core.database.dao.UserDao
import com.example.hairbook.core.database.entity.UserEntity
import com.example.hairbook.feature.auth.data.mapper.toDomain
import com.example.hairbook.feature.auth.domain.model.User
import com.example.hairbook.feature.auth.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : AuthRepository {
    override fun observeCurrentUser(): Flow<User?> {
        return userDao.observeCurrentUser().map { it?.toDomain() }
    }

    override suspend fun signIn(id: String, displayName: String, email: String?) {
        userDao.upsertUser(
            UserEntity(
                id = id,
                displayName = displayName,
                email = email
            )
        )
    }

    override suspend fun signOut() {
        userDao.clearUsers()
    }
}
