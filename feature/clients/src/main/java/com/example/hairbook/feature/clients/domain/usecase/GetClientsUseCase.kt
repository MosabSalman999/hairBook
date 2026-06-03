package com.example.hairbook.feature.clients.domain.usecase

import com.example.hairbook.feature.clients.domain.repository.ClientsRepository
import javax.inject.Inject

class GetClientsUseCase @Inject constructor(
    private val repository: ClientsRepository
) {
    operator fun invoke() = repository.observeClients()
}
