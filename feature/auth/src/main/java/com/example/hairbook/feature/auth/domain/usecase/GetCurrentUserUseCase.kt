package com.example.hairbook.feature.auth.domain.usecase

import com.example.hairbook.feature.auth.domain.repository.AuthRepository
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    operator fun invoke() = repository.observeCurrentUser()
}
