package com.evoionosp.windbreaker.core.network

import com.evoionosp.windbreaker.core.network.model.WeatherDetailsApiModel
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherDetailsApi {
    @GET("data/2.5/weather/")
    suspend fun getWeatherDetails(@Query("q") place: String): WeatherDetailsApiModel
}

//https://api.openweathermap.org/data/2.5/weather?q=Peterborough,CA&appid=5bb05088a28badc10bb4233394e12147