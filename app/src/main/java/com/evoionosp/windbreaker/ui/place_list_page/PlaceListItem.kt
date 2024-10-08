package com.evoionosp.windbreaker.ui.place_list_page

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.evoionosp.windbreaker.R
import com.evoionosp.windbreaker.core.data.model.WeatherDetails
import com.evoionosp.windbreaker.util.kelvinToCelsius


@Composable
fun PlaceListItem(
    place: WeatherDetails,
    onItemClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .clickable { onItemClick() }
            .padding(16.dp)
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.weather_icon),
            contentDescription = "weather icon",
            modifier = Modifier.size(48.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = place.city+","+place.country, style = MaterialTheme.typography.titleMedium)
            Text(text = place.weatherCondition, style = MaterialTheme.typography.bodyMedium)
            Text(text = "Temperature: ${kelvinToCelsius(place.temperature)}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Feels like: ${kelvinToCelsius(place.feelsLike)}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}