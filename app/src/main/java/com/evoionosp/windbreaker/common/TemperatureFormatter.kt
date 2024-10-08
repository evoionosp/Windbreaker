package com.evoionosp.windbreaker.util

import android.icu.text.MeasureFormat
import android.icu.text.MeasureFormat.FormatWidth
import android.icu.util.Measure
import android.icu.util.MeasureUnit
import android.icu.util.ULocale


var fmtCA: MeasureFormat = MeasureFormat.getInstance(ULocale.CANADA, FormatWidth.SHORT)

fun formatTemperature(value: Double, measureUnit: MeasureUnit): String {

    if(value.isNaN()){
        return ""
    }
    val measure = Measure(value, measureUnit)
    //val measure = Measure(value, MeasureUnit.FAHRENHEIT)
    return fmtCA.format(measure)
}

fun kelvinToCelsius(value: Double): String {
    if(value.isNaN()){
        return ""
    }
    return  formatTemperature(value - 273.15, MeasureUnit.CELSIUS)
}

fun kelvinToFahrenheit(value: Double): String {
    if(value.isNaN()){
        return ""
    }
    return  formatTemperature((value - 273.15) * 9 / 5 + 32, MeasureUnit.FAHRENHEIT)
}
