package com.example.hairbook.feature.gallery.ui

import com.example.hairbook.feature.gallery.domain.model.GalleryPhoto

data class GalleryUiState(
    val isLoading: Boolean = false,
    val photos: List<GalleryPhoto> = emptyList(),
    val errorMessage: String? = null
)
