package com.evoionosp.windbreaker.ui.main_page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.evoionosp.windbreaker.ui.components.SimpleTopAppBar
import com.evoionosp.windbreaker.ui.theme.AppTheme
import timber.log.Timber

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val viewModel = hiltViewModel<PlacesViewModel>()
    val uiState by viewModel.weatherListState.collectAsState(initial = WeatherListState())
    AppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {


                Column {
                    SimpleTopAppBar("Weather App")
                    if(uiState.isLoading){
                        Timber.tag(TAG).d("Loading ....")
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(200.dp),
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    } else {
                        Timber.tag(TAG).d("Weather loaded !")
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .background(
                                    color = MaterialTheme.colorScheme.background
                                )

                        ) {
//                    item {
//                        Spacer(modifier = Modifier.height(10.dp))
//                        Text(text = "Places", style = MaterialTheme.typography.headlineLarge)
//                    }
                            items(items = uiState.weatherList, itemContent = { content ->
                                PlaceWeatherListItem(place = content)
                            })
                        }

                    }
            }
        }
    }
}

const val TAG = "MainScreen"