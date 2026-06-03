package com.example.hairbook.feature.gallery.data.mapper

import com.example.hairbook.core.database.entity.GalleryPhotoEntity
import com.example.hairbook.feature.gallery.domain.model.GalleryPhoto

fun GalleryPhotoEntity.toDomain(): GalleryPhoto = GalleryPhoto(id, clientId, uri, caption, createdAtMillis)

fun GalleryPhoto.toEntity(): GalleryPhotoEntity = GalleryPhotoEntity(id, clientId, uri, caption, createdAtMillis)
