package com.example.hairbook.feature.gallery.domain.usecase

import com.example.hairbook.feature.gallery.domain.repository.GalleryRepository
import javax.inject.Inject

class DeleteGalleryPhotoUseCase @Inject constructor(
    private val repository: GalleryRepository
) {
    suspend operator fun invoke(id: String) = repository.deleteGalleryPhoto(id)
}
