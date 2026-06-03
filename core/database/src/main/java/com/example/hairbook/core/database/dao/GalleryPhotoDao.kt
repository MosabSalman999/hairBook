package com.example.hairbook.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.hairbook.core.database.entity.GalleryPhotoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GalleryPhotoDao {
    @Query("SELECT * FROM gallery_photos ORDER BY createdAtMillis DESC")
    fun observeGalleryPhotos(): Flow<List<GalleryPhotoEntity>>

    @Upsert
    suspend fun upsertGalleryPhoto(photo: GalleryPhotoEntity)

    @Query("DELETE FROM gallery_photos WHERE id = :id")
    suspend fun deleteGalleryPhoto(id: String)
}
