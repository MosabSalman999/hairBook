package com.example.hairbook.feature.clients.domain.usecase

import com.example.hairbook.feature.clients.domain.repository.ClientsRepository
import javax.inject.Inject

class GetClientUseCase @Inject constructor(
    private val repository: ClientsRepository
) {
    operator fun invoke(id: String) = repository.observeClient(id)
}
