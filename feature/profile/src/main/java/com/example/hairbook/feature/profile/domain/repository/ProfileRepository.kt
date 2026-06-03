package com.example.hairbook.feature.profile.domain.repository

import com.example.hairbook.feature.profile.domain.model.Profile
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    fun observeProfile(): Flow<Profile?>
    suspend fun updateProfile(profile: Profile)
}
