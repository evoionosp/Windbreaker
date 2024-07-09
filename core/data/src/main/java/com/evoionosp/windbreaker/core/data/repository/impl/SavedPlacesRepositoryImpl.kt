package com.evoionosp.windbreaker.core.data.repository.impl

import com.evoionosp.windbreaker.core.data.repository.SavedPlacesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject


class SavedPlacesRepositoryImpl @Inject constructor(): SavedPlacesRepository{

    override suspend fun getSavedCities() : Flow<List<String>> = flowOf(
        listOf(
            "Peterborough,CA",
            "Toronto,CA",
            "Vancouver,CA",
            "Calgary,CA",
            "Ottawa,CA",
            "Montreal,CA",
            "Edmonton,CA",
            "Vancouver,CA",
            "Vancouver,CA",
            "New York,US",
            "London,UK",
            "Paris,FR",
            "Tokyo,JP",
            "Sydney,AU",
            "Dubai,UAE",
            "Singapore,SG"
        )
    )

    companion object {
        private const val TAG = "SavedPlacesRepositoryImpl"
    }
}