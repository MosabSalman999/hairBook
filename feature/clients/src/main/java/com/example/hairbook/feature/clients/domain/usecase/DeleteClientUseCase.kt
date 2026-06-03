package com.example.hairbook.feature.clients.domain.usecase

import com.example.hairbook.feature.clients.domain.repository.ClientsRepository
import javax.inject.Inject

class DeleteClientUseCase @Inject constructor(
    private val repository: ClientsRepository
) {
    suspend operator fun invoke(id: String) = repository.deleteClient(id)
}
