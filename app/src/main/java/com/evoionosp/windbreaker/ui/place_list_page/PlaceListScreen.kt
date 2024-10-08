package com.evoionosp.windbreaker.ui.place_list_page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.pullToRefresh
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.evoionosp.windbreaker.ui.components.SimpleTopAppBar
import com.evoionosp.windbreaker.ui.details_page.WeatherDetailsPage
import kotlinx.serialization.Serializable
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaceListScreen(navController: NavController) {
    val viewModel = hiltViewModel<PlaceListViewModel>()
    val uiState by viewModel.weatherListState.collectAsState(initial = WeatherListState())
    val pullToRefreshState = rememberPullToRefreshState()
    val TAG = "PlaceListScreen"


    Scaffold(
        modifier = Modifier.pullToRefresh(
            state = pullToRefreshState,
            isRefreshing = uiState.isLoading,
            onRefresh = { viewModel.loadWeather() }
        ),
        topBar = { SimpleTopAppBar(title = "Weather App Imp")}

    ) { parentPadding ->

        PullToRefreshBox(
            modifier = Modifier.padding(parentPadding),
            state = pullToRefreshState,
            isRefreshing = uiState.isLoading,
            onRefresh = { viewModel.loadWeather() }
        ) {
            Timber.tag(TAG).d("Weather loaded !")

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = MaterialTheme.colorScheme.background
                    )

            ) {
                items(items = uiState.weatherList, itemContent = { content ->
                    PlaceListItem(
                        place = content,
                        onItemClick = { navController.navigate(WeatherDetailsPage(
                            place = content.city + ", " + content.country,
                            temperature = content.temperature,
                            feelsLike = content.feelsLike,
                            weatherCondition = content.weatherCondition,
                            windSpeed = content.windSpeed,
                            pressure = content.pressure,
                            humidity = content.humidity
                            ))
                            }
                    )
                })
            }

            if(uiState.isLoading){
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }
        }
    }
}

@Serializable
object PlacesListPage

