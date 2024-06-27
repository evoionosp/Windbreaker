package com.evoionosp.windbreaker.core.data.di

import com.evoionosp.windbreaker.core.data.repository.SavedPlacesRepository
import com.evoionosp.windbreaker.core.data.repository.WeatherNetworkRepository
import com.evoionosp.windbreaker.core.data.repository.impl.SavedPlacesRepositoryImpl
import com.evoionosp.windbreaker.core.data.repository.impl.WeatherNetworkRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataModule {


    @Provides
    fun provideWeatherDetailsRepository( weatherDetailsRepositoryImpl: WeatherNetworkRepositoryImpl): WeatherNetworkRepository {
        return weatherDetailsRepositoryImpl
    }


    @Provides
    fun provideSavedPlacesRepository(savedPlacesRepositoryImpl: SavedPlacesRepositoryImpl): SavedPlacesRepository {
        return savedPlacesRepositoryImpl
    }
}