package com.example.hairbook.feature.auth.domain.usecase

import com.example.hairbook.feature.auth.domain.repository.AuthRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(id: String, displayName: String, email: String?) {
        repository.signIn(id, displayName, email)
    }
}
