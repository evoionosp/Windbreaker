package com.evoionosp.windbreaker.core.model

data class WeatherDetails(
    val lat: Double = 0.0,
    val lon: Double = 0.0,
    val city : String = "",
    val country: String = "",
    val temperature : Double = 0.0,
    val temperatureMin: Double = 0.0,
    val temperatureMax: Double = 0.0,
    val windSpeed: Double = 0.0,
    val windAngle: Int = 0,
    val feelsLike: Double = 0.0,
    val humidity: Int = 0,
    val pressure: Int = 0,
    val weatherCondition: String = "",
    val weatherDescription: String = "",
    val sunrise: Long = 0,
    val sunset: Long = 0
)