package com.evoionosp.windbreaker.ui.details_page

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.toRoute
import com.evoionosp.windbreaker.core.data.model.WeatherDetails
import com.evoionosp.windbreaker.ui.components.NoNetwork
import com.evoionosp.windbreaker.ui.components.SimpleTopAppBar
import kotlinx.serialization.Serializable

@Composable
fun WeatherDetailsScreen(navController: NavController) {


    val weatherDetailsPage =  navController.getBackStackEntry<WeatherDetailsPage>().toRoute<WeatherDetailsPage>()



   // val viewModel = hiltViewModel<WeatherDetailsViewModel>()
    var uiState = WeatherDetailsUiState()

   uiState = uiState.copy(
        place = weatherDetailsPage.place,
        temp = weatherDetailsPage.temperature,
        feelsLike = weatherDetailsPage.feelsLike,
        weatherCondition = weatherDetailsPage.weatherCondition,
//        windSpeed = weatherDetailsPage.windSpeed,
//        pressure = weatherDetailsPage.pressure,
//        humidity = weatherDetailsPage.humidity,
    )


    Column {
        SimpleTopAppBar(title = weatherDetailsPage.place)
    }


    if (uiState.offline) {
        NoNetwork()
    } else {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp)
        ) {
            Column {
                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = uiState.place?: "",
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp),
                    text = uiState.temperature,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp),
                    text = uiState.temperatureMax,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp),
                    text = uiState.temperatureMin,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }


    }

@Serializable
data class WeatherDetailsPage(
    val place: String,
    val temperature: Double,
    val feelsLike: Double,
    val windSpeed: Double,
    val pressure: Int,
    val humidity: Int,
    val weatherCondition: String,
)
