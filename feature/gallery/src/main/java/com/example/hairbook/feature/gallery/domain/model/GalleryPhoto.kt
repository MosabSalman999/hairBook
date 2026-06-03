package com.example.hairbook.feature.gallery.domain.model

data class GalleryPhoto(
    val id: String,
    val clientId: String?,
    val uri: String,
    val caption: String?,
    val createdAtMillis: Long
)
