package com.example.hairbook.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hairbook.core.database.dao.FavouriteDao
import com.example.hairbook.core.database.dao.HairstyleDao
import com.example.hairbook.core.database.dao.UserDao
import com.example.hairbook.core.database.entity.FavouriteEntity
import com.example.hairbook.core.database.entity.HairstyleEntity
import com.example.hairbook.core.database.entity.UserEntity

@Database(
    entities = [
        HairstyleEntity::class,
        FavouriteEntity::class,
        UserEntity::class,
    ],
    version = 1,
    exportSchema = true
)
abstract class HairBookDatabase : RoomDatabase() {
    abstract fun hairstyleDao(): HairstyleDao
    abstract fun favouriteDao(): FavouriteDao
    abstract fun userDao(): UserDao
}
