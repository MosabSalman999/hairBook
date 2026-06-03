package com.example.hairbook.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "display_name") val displayName: String,
    @ColumnInfo(name = "created_at") val createdAt: Long,
    @ColumnInfo(name = "email") val email: String? = null,
    @ColumnInfo(name = "role") val role: String = "user",
    @ColumnInfo(name = "is_guest") val isGuest: Int = 0,
)
