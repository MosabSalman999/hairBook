package com.example.hairbook.core.database

import android.content.Context
import androidx.room.Room
import com.example.hairbook.core.database.dao.AppointmentDao
import com.example.hairbook.core.database.dao.ClientDao
import com.example.hairbook.core.database.dao.GalleryPhotoDao
import com.example.hairbook.core.database.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): HairBookDatabase {
        return Room.databaseBuilder(
            context,
            HairBookDatabase::class.java,
            "hairbook.db"
        ).build()
    }

    @Provides
    fun provideUserDao(database: HairBookDatabase): UserDao = database.userDao()

    @Provides
    fun provideClientDao(database: HairBookDatabase): ClientDao = database.clientDao()

    @Provides
    fun provideAppointmentDao(database: HairBookDatabase): AppointmentDao = database.appointmentDao()

    @Provides
    fun provideGalleryPhotoDao(database: HairBookDatabase): GalleryPhotoDao = database.galleryPhotoDao()
}
