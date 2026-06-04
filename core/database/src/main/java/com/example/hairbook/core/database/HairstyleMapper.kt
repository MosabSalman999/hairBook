package com.example.hairbook.core.database

import com.example.hairbook.core.database.entity.HairstyleEntity
import com.example.hairbook.core.domain.model.FaceShape
import com.example.hairbook.core.domain.model.Gender
import com.example.hairbook.core.domain.model.HairColour
import com.example.hairbook.core.domain.model.HairLength
import com.example.hairbook.core.domain.model.HairTexture
import com.example.hairbook.core.domain.model.HairThickness
import com.example.hairbook.core.domain.model.Hairstyle
import com.example.hairbook.core.domain.model.Product
import org.json.JSONArray
import org.json.JSONObject

fun HairstyleEntity.toDomain(): Hairstyle = Hairstyle(
    id = id,
    nameEn = nameEn,
    nameAr = nameAr,
    nameDe = nameDe,
    gender = Gender.valueOf(gender),
    category = category,
    heroImage = heroImage,
    gallery = jsonToStringList(gallery),
    descriptionEn = descriptionEn,
    descriptionAr = descriptionAr,
    descriptionDe = descriptionDe,
    faceShapes = jsonToEnumList(faceShapes) { FaceShape.valueOf(it) },
    texture = jsonToEnumList(texture) { HairTexture.valueOf(it) },
    lengths = jsonToEnumList(lengths) { HairLength.valueOf(it) },
    thickness = jsonToEnumList(thickness) { HairThickness.valueOf(it) },
    colours = jsonToEnumList(colours) { HairColour.valueOf(it) },
    timeMinutes = timeMinutes,
    difficulty = difficulty,
    products = jsonToProducts(products),
    sortOrder = sortOrder,
)

fun Hairstyle.toEntity(): HairstyleEntity = HairstyleEntity(
    id = id,
    nameEn = nameEn,
    nameAr = nameAr,
    nameDe = nameDe,
    gender = gender.name,
    category = category,
    heroImage = heroImage,
    gallery = stringListToJson(gallery),
    descriptionEn = descriptionEn,
    descriptionAr = descriptionAr,
    descriptionDe = descriptionDe,
    faceShapes = enumListToJson(faceShapes),
    texture = enumListToJson(texture),
    lengths = enumListToJson(lengths),
    thickness = enumListToJson(thickness),
    colours = enumListToJson(colours),
    timeMinutes = timeMinutes,
    difficulty = difficulty,
    products = productsToJson(products),
    sortOrder = sortOrder,
)

private fun jsonToStringList(json: String): List<String> {
    val array = JSONArray(json)
    return (0 until array.length()).map { array.getString(it) }
}

private fun <T> jsonToEnumList(json: String, converter: (String) -> T): List<T> {
    val array = JSONArray(json)
    return (0 until array.length()).mapNotNull {
        runCatching { converter(array.getString(it)) }.getOrNull()
    }
}

private fun jsonToProducts(json: String): List<Product> {
    val array = JSONArray(json)
    return (0 until array.length()).map {
        val obj = array.getJSONObject(it)
        Product(name = obj.getString("name"), type = obj.getString("type"))
    }
}

private fun stringListToJson(list: List<String>): String =
    JSONArray().also { arr -> list.forEach { arr.put(it) } }.toString()

private fun <T : Enum<T>> enumListToJson(list: List<T>): String =
    JSONArray().also { arr -> list.forEach { arr.put(it.name) } }.toString()

private fun productsToJson(list: List<Product>): String =
    JSONArray().also { arr ->
        list.forEach { p ->
            arr.put(JSONObject().apply { put("name", p.name); put("type", p.type) })
        }
    }.toString()
