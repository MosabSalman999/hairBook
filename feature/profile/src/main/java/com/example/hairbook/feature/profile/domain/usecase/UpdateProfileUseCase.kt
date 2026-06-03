package com.example.hairbook.feature.profile.domain.usecase

import com.example.hairbook.feature.profile.domain.model.Profile
import com.example.hairbook.feature.profile.domain.repository.ProfileRepository
import javax.inject.Inject

class UpdateProfileUseCase @Inject constructor(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(profile: Profile) = repository.updateProfile(profile)
}
