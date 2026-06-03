package com.example.hairbook.feature.gallery.data.repository

import com.example.hairbook.core.database.dao.GalleryPhotoDao
import com.example.hairbook.feature.gallery.data.mapper.toDomain
import com.example.hairbook.feature.gallery.data.mapper.toEntity
import com.example.hairbook.feature.gallery.domain.model.GalleryPhoto
import com.example.hairbook.feature.gallery.domain.repository.GalleryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GalleryRepositoryImpl @Inject constructor(
    private val galleryPhotoDao: GalleryPhotoDao
) : GalleryRepository {
    override fun observeGalleryPhotos(): Flow<List<GalleryPhoto>> {
        return galleryPhotoDao.observeGalleryPhotos().map { photos -> photos.map { it.toDomain() } }
    }

    override suspend fun saveGalleryPhoto(photo: GalleryPhoto) {
        galleryPhotoDao.upsertGalleryPhoto(photo.toEntity())
    }

    override suspend fun deleteGalleryPhoto(id: String) {
        galleryPhotoDao.deleteGalleryPhoto(id)
    }
}
