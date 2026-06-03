package com.example.hairbook.feature.profile.data.repository

import com.example.hairbook.core.database.dao.UserDao
import com.example.hairbook.feature.profile.data.mapper.toEntity
import com.example.hairbook.feature.profile.data.mapper.toProfile
import com.example.hairbook.feature.profile.domain.model.Profile
import com.example.hairbook.feature.profile.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : ProfileRepository {
    override fun observeProfile(): Flow<Profile?> = userDao.observeCurrentUser().map { it?.toProfile() }

    override suspend fun updateProfile(profile: Profile) {
        userDao.upsertUser(profile.toEntity())
    }
}
