package com.example.hairbook.core.database

import android.content.Context
import com.example.hairbook.core.database.entity.HairstyleEntity
import dagger.hilt.android.qualifiers.ApplicationContext
import org.json.JSONArray
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JsonLoader @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    fun loadHairstyleEntities(): List<HairstyleEntity> {
        val json = context.assets.open("hairstyles.json")
            .bufferedReader()
            .use { it.readText() }
        val array = JSONArray(json)
        return (0 until array.length()).map { i ->
            val obj = array.getJSONObject(i)
            HairstyleEntity(
                id = obj.getString("id"),
                nameEn = obj.getString("name_en"),
                nameAr = obj.getString("name_ar"),
                nameDe = obj.getString("name_de"),
                gender = obj.getString("gender"),
                category = obj.getString("category"),
                heroImage = obj.getString("hero_image"),
                gallery = obj.getJSONArray("gallery").toString(),
                descriptionEn = obj.getString("description_en"),
                descriptionAr = obj.getString("description_ar"),
                descriptionDe = obj.getString("description_de"),
                faceShapes = obj.getJSONArray("face_shapes").toString(),
                texture = obj.getJSONArray("texture").toString(),
                lengths = obj.getJSONArray("lengths").toString(),
                thickness = obj.getJSONArray("thickness").toString(),
                colours = obj.getJSONArray("colours").toString(),
                timeMinutes = obj.getInt("time_minutes"),
                difficulty = obj.getInt("difficulty"),
                products = obj.getJSONArray("products").toString(),
                sortOrder = obj.getInt("sort_order"),
            )
        }
    }
}
