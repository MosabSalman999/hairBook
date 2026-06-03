package com.example.hairbook.feature.gallery.domain.usecase

import com.example.hairbook.feature.gallery.domain.model.GalleryPhoto
import com.example.hairbook.feature.gallery.domain.repository.GalleryRepository
import javax.inject.Inject

class SaveGalleryPhotoUseCase @Inject constructor(
    private val repository: GalleryRepository
) {
    suspend operator fun invoke(photo: GalleryPhoto) = repository.saveGalleryPhoto(photo)
}
