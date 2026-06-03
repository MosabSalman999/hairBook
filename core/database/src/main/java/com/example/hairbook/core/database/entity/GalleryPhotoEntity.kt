package com.example.hairbook.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gallery_photos")
data class GalleryPhotoEntity(
    @PrimaryKey val id: String,
    val clientId: String? = null,
    val uri: String,
    val caption: String? = null,
    val createdAtMillis: Long
)
