package com.example.hairbook.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.hairbook.core.database.converter.DateTimeConverters
import com.example.hairbook.core.database.dao.AppointmentDao
import com.example.hairbook.core.database.dao.ClientDao
import com.example.hairbook.core.database.dao.GalleryPhotoDao
import com.example.hairbook.core.database.dao.UserDao
import com.example.hairbook.core.database.entity.AppointmentEntity
import com.example.hairbook.core.database.entity.ClientEntity
import com.example.hairbook.core.database.entity.GalleryPhotoEntity
import com.example.hairbook.core.database.entity.UserEntity

@Database(
    entities = [
        UserEntity::class,
        ClientEntity::class,
        AppointmentEntity::class,
        GalleryPhotoEntity::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(DateTimeConverters::class)
abstract class HairBookDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun clientDao(): ClientDao
    abstract fun appointmentDao(): AppointmentDao
    abstract fun galleryPhotoDao(): GalleryPhotoDao
}
