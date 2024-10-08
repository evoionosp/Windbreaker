package com.evoionosp.windbreaker.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.evoionosp.windbreaker.ui.entry.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.tag(TAG).d("Weather App Main Activity")
        setContent {
            EntryPoint()
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}