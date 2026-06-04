package com.example.hairbook.core.domain.model

data class HaircutCategory(
    val id: String,
    val nameEn: String,
    val nameAr: String,
    val nameDe: String,
    val coverImage: String,
    val gender: Gender?,
)
