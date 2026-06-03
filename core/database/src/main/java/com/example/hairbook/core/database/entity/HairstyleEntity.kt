package com.example.hairbook.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hairstyles")
data class HairstyleEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "name_en") val nameEn: String,
    @ColumnInfo(name = "name_ar") val nameAr: String,
    @ColumnInfo(name = "name_de") val nameDe: String,

    @ColumnInfo(name = "gender") val gender: String,

    @ColumnInfo(name = "hero_image") val heroImage: String,
    @ColumnInfo(name = "gallery") val gallery: String,           // JSON array of image paths

    @ColumnInfo(name = "description_en") val descriptionEn: String,
    @ColumnInfo(name = "description_ar") val descriptionAr: String,
    @ColumnInfo(name = "description_de") val descriptionDe: String,

    @ColumnInfo(name = "face_shapes") val faceShapes: String,   // JSON array of enum names
    @ColumnInfo(name = "texture") val texture: String,           // JSON array of enum names
    @ColumnInfo(name = "lengths") val lengths: String,           // JSON array of enum names
    @ColumnInfo(name = "thickness") val thickness: String,       // JSON array of enum names
    @ColumnInfo(name = "colours") val colours: String,           // JSON array of enum names

    @ColumnInfo(name = "time_minutes") val timeMinutes: Int,
    @ColumnInfo(name = "difficulty") val difficulty: Int,

    @ColumnInfo(name = "products") val products: String,         // JSON array of {name, type}

    @ColumnInfo(name = "sort_order") val sortOrder: Int,
)
