package com.example.hairbook.feature.gallery.data

import com.example.hairbook.feature.gallery.data.remote.GalleryApi
import com.example.hairbook.feature.gallery.data.repository.GalleryRepositoryImpl
import com.example.hairbook.feature.gallery.domain.repository.GalleryRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class GalleryBindingModule {
    @Binds
    @Singleton
    abstract fun bindGalleryRepository(impl: GalleryRepositoryImpl): GalleryRepository
}

@Module
@InstallIn(SingletonComponent::class)
object GalleryModule {
    @Provides
    @Singleton
    fun provideGalleryApi(retrofit: Retrofit): GalleryApi = retrofit.create(GalleryApi::class.java)
}
