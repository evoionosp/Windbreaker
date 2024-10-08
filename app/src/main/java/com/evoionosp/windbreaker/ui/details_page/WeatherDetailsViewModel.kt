package com.evoionosp.windbreaker.ui.details_page

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evoionosp.windbreaker.core.data.repository.WeatherNetworkRepository
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class WeatherDetailsViewModel @Inject constructor(
    private val weatherNetworkRepository: WeatherNetworkRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var uiState by mutableStateOf(WeatherDetailsUiState())
        public set



    fun loadWeatherDetails() {
        uiState.place?.let {
            viewModelScope.launch(Dispatchers.IO) {
                weatherNetworkRepository.getWeatherDetails(it).collect { response ->
                    withContext(Dispatchers.Main) {
                        uiState = uiState.copy(
                            temp = response.temperature,
                            tempMin = response.temperatureMin,
                            tempMax = response.temperatureMax,
                            weatherCondition = response.weatherCondition,
                            offline = false
                        )
                    }
                }
            }
        }
    }

}