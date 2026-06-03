package com.example.hairbook.feature.gallery.domain.repository

import com.example.hairbook.feature.gallery.domain.model.GalleryPhoto
import kotlinx.coroutines.flow.Flow

interface GalleryRepository {
    fun observeGalleryPhotos(): Flow<List<GalleryPhoto>>
    suspend fun saveGalleryPhoto(photo: GalleryPhoto)
    suspend fun deleteGalleryPhoto(id: String)
}
