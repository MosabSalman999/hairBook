package com.example.hairbook.feature.profile.domain.usecase

import com.example.hairbook.feature.profile.domain.repository.ProfileRepository
import javax.inject.Inject

class GetProfileUseCase @Inject constructor(
    private val repository: ProfileRepository
) {
    operator fun invoke() = repository.observeProfile()
}
