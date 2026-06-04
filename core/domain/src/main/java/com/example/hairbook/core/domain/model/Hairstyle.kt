package com.example.hairbook.core.domain.model

data class Hairstyle(
    val id: String,
    val nameEn: String,
    val nameAr: String,
    val nameDe: String,
    val gender: Gender,
    val heroImage: String,
    val gallery: List<String>,
    val descriptionEn: String,
    val descriptionAr: String,
    val descriptionDe: String,
    val faceShapes: List<FaceShape>,
    val texture: List<HairTexture>,
    val lengths: List<HairLength>,
    val thickness: List<HairThickness>,
    val colours: List<HairColour>,
    val timeMinutes: Int,
    val difficulty: Int,
    val products: List<Product>,
    val sortOrder: Int,
)
