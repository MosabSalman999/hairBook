package com.example.hairbook.core.database

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.hairbook.core.database.dao.FavouriteDao
import com.example.hairbook.core.database.dao.HairstyleDao
import com.example.hairbook.core.database.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("ALTER TABLE hairstyles ADD COLUMN category TEXT NOT NULL DEFAULT 'general'")
    }
}

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
        )
            .addMigrations(MIGRATION_1_2)
            .build()
    }

    @Provides
    fun provideHairstyleDao(database: HairBookDatabase): HairstyleDao = database.hairstyleDao()

    @Provides
    fun provideFavouriteDao(database: HairBookDatabase): FavouriteDao = database.favouriteDao()

    @Provides
    fun provideUserDao(database: HairBookDatabase): UserDao = database.userDao()
}
