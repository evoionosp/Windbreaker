package com.evoionosp.windbreaker.core.data.repository

import com.evoionosp.windbreaker.core.model.WeatherDetails
import kotlinx.coroutines.flow.Flow


interface WeatherNetworkRepository {

    suspend fun getWeatherList() : Flow<List<WeatherDetails>>

    suspend fun getWeatherDetails(place: String): Flow<WeatherDetails>

}