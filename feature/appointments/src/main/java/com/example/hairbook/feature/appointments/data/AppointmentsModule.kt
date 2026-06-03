package com.example.hairbook.feature.appointments.data

import com.example.hairbook.feature.appointments.data.remote.AppointmentsApi
import com.example.hairbook.feature.appointments.data.repository.AppointmentsRepositoryImpl
import com.example.hairbook.feature.appointments.domain.repository.AppointmentsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppointmentsBindingModule {
    @Binds
    @Singleton
    abstract fun bindAppointmentsRepository(impl: AppointmentsRepositoryImpl): AppointmentsRepository
}

@Module
@InstallIn(SingletonComponent::class)
object AppointmentsModule {
    @Provides
    @Singleton
    fun provideAppointmentsApi(retrofit: Retrofit): AppointmentsApi = retrofit.create(AppointmentsApi::class.java)
}
