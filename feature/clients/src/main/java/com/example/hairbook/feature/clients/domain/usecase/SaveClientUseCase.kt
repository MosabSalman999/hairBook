package com.example.hairbook.feature.clients.domain.usecase

import com.example.hairbook.feature.clients.domain.model.Client
import com.example.hairbook.feature.clients.domain.repository.ClientsRepository
import javax.inject.Inject

class SaveClientUseCase @Inject constructor(
    private val repository: ClientsRepository
) {
    suspend operator fun invoke(client: Client) = repository.saveClient(client)
}
