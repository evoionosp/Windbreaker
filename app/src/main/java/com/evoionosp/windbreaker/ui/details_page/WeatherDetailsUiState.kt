package com.evoionosp.windbreaker.ui.details_page

import android.icu.util.MeasureUnit
import com.evoionosp.windbreaker.core.data.model.WeatherDetails
import com.evoionosp.windbreaker.util.formatTemperature
import kotlinx.serialization.Serializable

@Serializable
data class  WeatherDetailsUiState(
    val place: String?,
    val offline: Boolean = false,
    private val temp: Double = Double.NaN,
    private val feelsLike: Double = Double.NaN,
    private val tempMin: Double = Double.NaN,
    private val tempMax: Double = Double.NaN,
    val weatherCondition: String?,
    val weatherDescription: String?
) {
    val temperature: String = formatTemperature(temp, MeasureUnit.CELSIUS)
    val temperatureFeelsLike: String = formatTemperature(feelsLike, MeasureUnit.CELSIUS)
    val temperatureMin: String = formatTemperature(tempMin, MeasureUnit.CELSIUS)
    val temperatureMax: String = formatTemperature(tempMax, MeasureUnit.CELSIUS)
}