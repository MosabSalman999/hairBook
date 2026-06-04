package com.example.hairbook.core.database.repository

import com.example.hairbook.core.database.dao.UserDao
import com.example.hairbook.core.database.entity.UserEntity
import com.example.hairbook.core.domain.model.User
import com.example.hairbook.core.domain.model.UserRole
import com.example.hairbook.core.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val dao: UserDao,
) : UserRepository {

    override fun observeCurrentUser(): Flow<User?> =
        dao.observeCurrentUser().map { it?.toDomain() }

    override suspend fun saveUser(user: User) = dao.upsertUser(user.toEntity())

    override suspend fun clearCurrentUser() = dao.clearUsers()
}

private fun UserEntity.toDomain() = User(
    id = id,
    displayName = displayName,
    createdAt = createdAt,
    email = email,
    role = runCatching { UserRole.valueOf(role) }.getOrDefault(UserRole.USER),
    isGuest = isGuest == 1,
)

private fun User.toEntity() = UserEntity(
    id = id,
    displayName = displayName,
    createdAt = createdAt,
    email = email,
    role = role.name,
    isGuest = if (isGuest) 1 else 0,
)
