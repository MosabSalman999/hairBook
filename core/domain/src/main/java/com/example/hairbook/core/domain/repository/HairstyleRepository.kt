package com.example.hairbook.core.domain.repository

import com.example.hairbook.core.domain.model.Gender
import com.example.hairbook.core.domain.model.Hairstyle
import kotlinx.coroutines.flow.Flow

interface HairstyleRepository {
    fun observeHairstyles(): Flow<List<Hairstyle>>
    fun observeHairstylesByGender(gender: Gender): Flow<List<Hairstyle>>
    fun observeHairstyle(id: String): Flow<Hairstyle?>

    suspend fun saveHairstyle(hairstyle: Hairstyle)
    suspend fun deleteHairstyle(hairstyle: Hairstyle)
}
