package com.example.hairbook.feature.clients.data

import com.example.hairbook.feature.clients.data.remote.ClientsApi
import com.example.hairbook.feature.clients.data.repository.ClientsRepositoryImpl
import com.example.hairbook.feature.clients.domain.repository.ClientsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ClientsBindingModule {
    @Binds
    @Singleton
    abstract fun bindClientsRepository(impl: ClientsRepositoryImpl): ClientsRepository
}

@Module
@InstallIn(SingletonComponent::class)
object ClientsModule {
    @Provides
    @Singleton
    fun provideClientsApi(retrofit: Retrofit): ClientsApi = retrofit.create(ClientsApi::class.java)
}
