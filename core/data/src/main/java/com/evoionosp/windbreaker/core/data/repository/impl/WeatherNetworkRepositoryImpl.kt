package com.evoionosp.windbreaker.core.data.repository.impl

import com.evoionosp.windbreaker.core.data.repository.SavedPlacesRepository
import com.evoionosp.windbreaker.core.data.repository.WeatherNetworkRepository
import com.evoionosp.windbreaker.core.data.model.WeatherDetails
import com.evoionosp.windbreaker.core.network.WeatherDetailsApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class WeatherNetworkRepositoryImpl @Inject constructor(
    private val weatherDetailsApi: WeatherDetailsApi,
    private val savedPlacesRepository: SavedPlacesRepository
) : WeatherNetworkRepository {

    override suspend fun getWeatherList() : Flow<List<WeatherDetails>> = flow {
        val weatherDetailsList = arrayListOf<WeatherDetails>()
        savedPlacesRepository.getSavedCities().collect {
            Timber.tag(TAG).d("Saved cities: %s", it.toString())
            it.mapIndexed{ index, place ->
                getWeatherDetails(place).collectLatest { weatherData ->
                    weatherDetailsList.add(index, weatherData)
                }
            }
        }

        this.emit(weatherDetailsList)
    }

    override suspend fun getWeatherDetails(place: String): Flow<WeatherDetails> = flow {
        try {
            val tmp = weatherDetailsApi.getWeatherDetails(place)
            Timber.tag(TAG).d("Network call response: %s", tmp.toString())
            val weatherDetails = WeatherDetails(
                lat = tmp.coordinates.lat,
                lon = tmp.coordinates.lon,
                city = tmp.name,
                country = tmp.countryLocal.country,
                temperature= tmp.main.temp,
                temperatureMin = tmp.main.tempMin,
                temperatureMax = tmp.main.tempMax,
                humidity = tmp.main.humidity,
                pressure = tmp.main.pressure,
                windSpeed = tmp.wind.speed,
                windAngle = tmp.wind.degree,
                feelsLike = tmp.main.feelsLike,
                weatherCondition = tmp.weatherList[0].main ?: "",
                weatherDescription = tmp.weatherList[0].description ?: "",
                sunrise = tmp.countryLocal.sunrise,
                sunset = tmp.countryLocal.sunset
            )
            emit(weatherDetails)
        }
        catch (illegalArgumentException: IllegalArgumentException){
            Timber.tag(TAG).d("IllegalArgumentException occurred: %s",illegalArgumentException.localizedMessage)
            illegalArgumentException.printStackTrace()
        }
        catch (exception: Exception){
            Timber.tag(TAG).d("Exception occurred: %s", exception.localizedMessage)
            exception.printStackTrace()
        }

    }

    companion object {
        private const val TAG = "WeatherDetailsRepositoryImpl"
    }
}