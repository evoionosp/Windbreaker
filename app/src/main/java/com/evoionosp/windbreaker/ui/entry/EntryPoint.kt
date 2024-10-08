package com.evoionosp.windbreaker.ui.entry

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.evoionosp.windbreaker.ui.place_list_page.PlaceListScreen
import androidx.navigation.compose.composable
import com.evoionosp.windbreaker.ui.details_page.WeatherDetailsPage
import com.evoionosp.windbreaker.ui.details_page.WeatherDetailsScreen
import com.evoionosp.windbreaker.ui.place_list_page.PlacesListPage


@Composable
fun EntryPoint() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = PlacesListPage) {
        composable<PlacesListPage> { PlaceListScreen(navController) }
        composable<WeatherDetailsPage> { WeatherDetailsScreen(navController) }
    }
}