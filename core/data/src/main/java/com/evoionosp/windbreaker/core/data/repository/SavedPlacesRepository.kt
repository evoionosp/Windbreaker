package com.evoionosp.windbreaker.core.data.repository

import kotlinx.coroutines.flow.Flow

interface SavedPlacesRepository  {

    suspend fun getSavedCities() : Flow<List<String>>

}