package com.example.hairbook.feature.clients.domain.repository

import com.example.hairbook.feature.clients.domain.model.Client
import kotlinx.coroutines.flow.Flow

interface ClientsRepository {
    fun observeClients(): Flow<List<Client>>
    fun observeClient(id: String): Flow<Client?>
    suspend fun saveClient(client: Client)
    suspend fun deleteClient(id: String)
}
