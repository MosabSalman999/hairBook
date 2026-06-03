package com.example.hairbook.feature.gallery.domain.usecase

import com.example.hairbook.feature.gallery.domain.repository.GalleryRepository
import javax.inject.Inject

class GetGalleryPhotosUseCase @Inject constructor(
    private val repository: GalleryRepository
) {
    operator fun invoke() = repository.observeGalleryPhotos()
}
