package com.example.hairbook.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hairbook.core.database.entity.FavouriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteDao {

    @Query("SELECT * FROM favourites WHERE user_id = :userId ORDER BY saved_at DESC")
    fun observeByUser(userId: String): Flow<List<FavouriteEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM favourites WHERE user_id = :userId AND hairstyle_id = :hairstyleId)")
    fun observeIsFavourite(userId: String, hairstyleId: String): Flow<Boolean>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(favourite: FavouriteEntity)

    @Query("DELETE FROM favourites WHERE user_id = :userId AND hairstyle_id = :hairstyleId")
    suspend fun delete(userId: String, hairstyleId: String)
}
