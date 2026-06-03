package com.example.hairbook.feature.clients.data.repository

import com.example.hairbook.core.database.dao.ClientDao
import com.example.hairbook.feature.clients.data.mapper.toDomain
import com.example.hairbook.feature.clients.data.mapper.toEntity
import com.example.hairbook.feature.clients.domain.model.Client
import com.example.hairbook.feature.clients.domain.repository.ClientsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ClientsRepositoryImpl @Inject constructor(
    private val clientDao: ClientDao
) : ClientsRepository {
    override fun observeClients(): Flow<List<Client>> = clientDao.observeClients().map { clients ->
        clients.map { it.toDomain() }
    }

    override fun observeClient(id: String): Flow<Client?> = clientDao.observeClient(id).map { it?.toDomain() }

    override suspend fun saveClient(client: Client) = clientDao.upsertClient(client.toEntity())

    override suspend fun deleteClient(id: String) = clientDao.deleteClient(id)
}
