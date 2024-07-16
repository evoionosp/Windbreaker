package com.evoionosp.windbreaker.ui.main_page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evoionosp.windbreaker.core.data.repository.WeatherNetworkRepository
import com.evoionosp.windbreaker.core.data.model.WeatherDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject


data class WeatherListState(
    val weatherList: List<WeatherDetails> = emptyList(),
    val isLoading: Boolean = false
)
@HiltViewModel
class PlacesViewModel @Inject constructor(
    private val weatherNetworkRepository: WeatherNetworkRepository
) : ViewModel() {


    private val _weatherListState = MutableStateFlow(WeatherListState())

    val weatherListState = _weatherListState.asStateFlow()

    private val loadWeatherJob = viewModelScope.launch(Dispatchers.IO) {
        Timber.tag(TAG).d("Loading set: true")
        _weatherListState.value = _weatherListState.value.copy(isLoading = true)
        weatherNetworkRepository.getWeatherList().collectLatest { weatherList ->
            withContext(Dispatchers.Main) {
                _weatherListState.value =   _weatherListState.value.copy(weatherList = weatherList)
                Timber.tag(TAG).d("weatherList Updated: itemCount: ${weatherList.size}")
            }
        }.also {
            _weatherListState.value = _weatherListState.value.copy(isLoading = false)
            Timber.tag(TAG).d("Loading set: false")
        }
    }

    fun loadWeather() {
        Timber.tag(TAG).d("onRefresh")
        if(!loadWeatherJob.isActive) {
            loadWeatherJob.start()
        } else {
            Timber.tag(TAG).d("Already running previous job")
        }
    }

    init {
        loadWeather()
    }


    override fun onCleared() {
        super.onCleared()
        Timber.tag(TAG).d("onCleared")
        loadWeatherJob.cancel()
        _weatherListState.value = _weatherListState.value.copy(isLoading = false)
    }



    companion object {
        const val TAG = "PlacesViewModel"
    }

}