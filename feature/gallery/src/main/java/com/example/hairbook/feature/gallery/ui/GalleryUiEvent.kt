package com.example.hairbook.feature.gallery.ui

sealed interface GalleryUiEvent {
    data object AddPhotoClicked : GalleryUiEvent
}
