package com.example.hairbook.feature.gallery.data.remote

data class GalleryPhotoDto(
    val id: String,
    val clientId: String?,
    val uri: String,
    val caption: String?,
    val createdAtMillis: Long
)
