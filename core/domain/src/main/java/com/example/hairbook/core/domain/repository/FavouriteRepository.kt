package com.example.hairbook.core.domain.repository

import com.example.hairbook.core.domain.model.Hairstyle
import kotlinx.coroutines.flow.Flow

interface FavouriteRepository {
    fun observeFavourites(userId: String): Flow<List<Hairstyle>>
    fun observeIsFavourite(userId: String, hairstyleId: String): Flow<Boolean>

    suspend fun addFavourite(userId: String, hairstyleId: String)
    suspend fun removeFavourite(userId: String, hairstyleId: String)
}
