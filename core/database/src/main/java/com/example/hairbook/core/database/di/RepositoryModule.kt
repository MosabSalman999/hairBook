package com.example.hairbook.core.database.di

import com.example.hairbook.core.database.repository.FavouriteRepositoryImpl
import com.example.hairbook.core.database.repository.HairstyleRepositoryImpl
import com.example.hairbook.core.database.repository.UserRepositoryImpl
import com.example.hairbook.core.domain.repository.FavouriteRepository
import com.example.hairbook.core.domain.repository.HairstyleRepository
import com.example.hairbook.core.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindHairstyleRepository(impl: HairstyleRepositoryImpl): HairstyleRepository

    @Binds
    @Singleton
    abstract fun bindFavouriteRepository(impl: FavouriteRepositoryImpl): FavouriteRepository

    @Binds
    @Singleton
    abstract fun bindUserRepository(impl: UserRepositoryImpl): UserRepository
}
