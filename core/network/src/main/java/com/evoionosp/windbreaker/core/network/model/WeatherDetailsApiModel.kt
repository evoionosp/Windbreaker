package com.evoionosp.windbreaker.core.network.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherDetailsApiModel(
    @Json(name = "coord")
    val coordinates: Coordinates = Coordinates(),
    @Json(name = "dt")
    val date: Long = 0L,
    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "main")
    val main: Main = Main(),
    @Json(name = "name")
    val name: String = "",
    @Json(name = "sys")
    val countryLocal: CountryLocal = CountryLocal(),
    @Json(name = "timezone")
    val timezone: Int = 0,
    @Json(name = "visibility")
    val visibility: Int = 0,
    @Json(name = "weather")
    val weatherList: List<Weather> = listOf(),
    @Json(name = "wind")
    val wind: Wind = Wind()
)

@JsonClass(generateAdapter = true)
data class Main(
    @Json(name = "feels_like")
    val feelsLike: Double = 0.0,
    @Json(name = "humidity")
    val humidity: Int = 0,
    @Json(name = "pressure")
    val pressure: Int = 0,
    @Json(name = "temp")
    val temp: Double = 0.0,
    @Json(name = "temp_max")
    val tempMax: Double = 0.0,
    @Json(name = "temp_min")
    val tempMin: Double = 0.0
)

@JsonClass(generateAdapter = true)
data class Coordinates(
    @Json(name = "lat")
    val lat: Double = 0.0,
    @Json(name = "lon")
    val lon: Double = 0.0
)

@JsonClass(generateAdapter = true)
data class Weather(
    @Json(name = "description")
    val description: String? = null,
    @Json(name = "icon")
    val icon: String? = null,
    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "main")
    val main: String? = null
)

@JsonClass(generateAdapter = true)
data class Wind(
    @Json(name = "deg")
    val degree: Int = 0,
    @Json(name = "speed")
    val speed: Double = 0.0
)

@JsonClass(generateAdapter = true)
data class CountryLocal(
    @Json(name = "country")
    val country: String = "",
    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "sunrise")
    val sunrise: Long = 0,
    @Json(name = "sunset")
    val sunset: Long = 0,
    @Json(name = "type")
    val type: Int = 0
)
