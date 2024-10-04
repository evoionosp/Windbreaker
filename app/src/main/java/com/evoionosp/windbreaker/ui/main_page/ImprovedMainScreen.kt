package com.evoionosp.windbreaker.ui.main_page

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.pullToRefresh
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.hilt.navigation.compose.hiltViewModel
import com.evoionosp.windbreaker.ui.components.SimpleTopAppBar
import com.evoionosp.windbreaker.ui.theme.AppTheme
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImprovedMainScreen(modifier: Modifier = Modifier) {
    val viewModel = hiltViewModel<PlacesViewModel>()
    val uiState by viewModel.weatherListState.collectAsState(initial = WeatherListState())
    val pullToRefreshState = rememberPullToRefreshState()

    val TAG = "ImprovedMainScreen"

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
                    PlaceWeatherListItem(place = content)
                })
            }

            if(uiState.isLoading){
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }
        }
    }
}
