package com.example.hairbook.core.database.repository

import com.example.hairbook.core.database.dao.FavouriteDao
import com.example.hairbook.core.database.dao.HairstyleDao
import com.example.hairbook.core.database.entity.FavouriteEntity
import com.example.hairbook.core.database.toDomain
import com.example.hairbook.core.domain.model.Hairstyle
import com.example.hairbook.core.domain.repository.FavouriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavouriteRepositoryImpl @Inject constructor(
    private val favouriteDao: FavouriteDao,
    private val hairstyleDao: HairstyleDao,
) : FavouriteRepository {

    override fun observeFavourites(userId: String): Flow<List<Hairstyle>> =
        favouriteDao.observeByUser(userId).combine(hairstyleDao.observeAll()) { favs, styles ->
            val favIds = favs.map { it.hairstyleId }.toSet()
            styles.filter { it.id in favIds }.map { it.toDomain() }
        }

    override fun observeIsFavourite(userId: String, hairstyleId: String): Flow<Boolean> =
        favouriteDao.observeIsFavourite(userId, hairstyleId)

    override suspend fun addFavourite(userId: String, hairstyleId: String) {
        favouriteDao.insert(
            FavouriteEntity(
                userId = userId,
                hairstyleId = hairstyleId,
                savedAt = System.currentTimeMillis(),
            )
        )
    }

    override suspend fun removeFavourite(userId: String, hairstyleId: String) =
        favouriteDao.delete(userId, hairstyleId)
}
