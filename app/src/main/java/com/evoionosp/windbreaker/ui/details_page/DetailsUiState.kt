package com.evoionosp.windbreaker.ui.details_page

import android.icu.util.MeasureUnit
import com.evoionosp.windbreaker.core.model.WeatherDetails
import com.evoionosp.windbreaker.util.formatTemperature

data class  DetailsUiState(
    val details: WeatherDetails = WeatherDetails(),
    val offline: Boolean = false
) {
    val name = details.city
    val fTemp: String = formatTemperature(details.temperature, MeasureUnit.CELSIUS)
    val fTempMin: String = formatTemperature(details.temperatureMin, MeasureUnit.CELSIUS)
    val fTempMax: String = formatTemperature(details.temperatureMax, MeasureUnit.CELSIUS)
}