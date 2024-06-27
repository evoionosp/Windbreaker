package com.evoionosp.windbreaker.ui.details_page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.evoionosp.windbreaker.ui.components.NoNetwork

@Composable
fun DetailsScreen() {
    val viewModel = hiltViewModel<DetailsViewModel>()
    val uiState = viewModel.uiState

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
                    text = uiState.details.city,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp),
                    text = uiState.fTemp,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp),
                    text = uiState.fTempMax,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp),
                    text = uiState.fTempMin,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}