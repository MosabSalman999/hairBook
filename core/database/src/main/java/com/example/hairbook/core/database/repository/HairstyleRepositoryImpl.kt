package com.example.hairbook.core.database.repository

import com.example.hairbook.core.database.JsonLoader
import com.example.hairbook.core.database.dao.HairstyleDao
import com.example.hairbook.core.database.toDomain
import com.example.hairbook.core.database.toEntity
import com.example.hairbook.core.domain.model.Gender
import com.example.hairbook.core.domain.model.Hairstyle
import com.example.hairbook.core.domain.repository.HairstyleRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HairstyleRepositoryImpl @Inject constructor(
    private val dao: HairstyleDao,
    private val jsonLoader: JsonLoader,
) : HairstyleRepository {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    init {
        scope.launch { seedIfEmpty() }
    }

    override fun observeHairstyles(): Flow<List<Hairstyle>> =
        dao.observeAll().map { list -> list.map { it.toDomain() } }

    override fun observeHairstylesByGender(gender: Gender): Flow<List<Hairstyle>> =
        dao.observeByGender(gender.name).map { list -> list.map { it.toDomain() } }

    override fun observeHairstyle(id: String): Flow<Hairstyle?> =
        dao.observeById(id).map { it?.toDomain() }

    override suspend fun saveHairstyle(hairstyle: Hairstyle) =
        dao.upsert(hairstyle.toEntity())

    override suspend fun deleteHairstyle(hairstyle: Hairstyle) =
        dao.delete(hairstyle.toEntity())

    private suspend fun seedIfEmpty() {
        if (dao.countAll() == 0) {
            dao.insertAll(jsonLoader.loadHairstyleEntities())
        }
    }
}
