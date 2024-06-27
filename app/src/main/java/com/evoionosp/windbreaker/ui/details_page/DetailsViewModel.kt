package com.evoionosp.windbreaker.ui.details_page

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evoionosp.windbreaker.core.data.repository.WeatherNetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val weatherNetworkRepository: WeatherNetworkRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private var place: String? = "Peterborough,CA"
    var uiState by mutableStateOf(DetailsUiState())
        private set

    init {
        place?.let {
            viewModelScope.launch(Dispatchers.IO) {
                weatherNetworkRepository.getWeatherDetails(it).collect { detail ->
                    withContext(Dispatchers.Main) {
                        uiState = uiState.copy(
                            details = detail,
                            offline = false
                        )
                    }
                }
            }
        }
    }

}