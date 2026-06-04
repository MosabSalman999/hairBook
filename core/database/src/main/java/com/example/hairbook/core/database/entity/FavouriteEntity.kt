package com.example.hairbook.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "favourites",
    indices = [Index(value = ["user_id", "hairstyle_id"], unique = true)]
)
data class FavouriteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,

    @ColumnInfo(name = "user_id") val userId: String,
    @ColumnInfo(name = "hairstyle_id") val hairstyleId: String,
    @ColumnInfo(name = "saved_at") val savedAt: Long,
)
