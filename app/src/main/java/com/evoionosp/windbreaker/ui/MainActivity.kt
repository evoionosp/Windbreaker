package com.evoionosp.windbreaker.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.evoionosp.windbreaker.ui.main_page.ImprovedMainScreen
import com.evoionosp.windbreaker.ui.main_page.MainScreen
import com.evoionosp.windbreaker.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Timber.tag(TAG).d("Weather App Mail Activity")
                ImprovedMainScreen()
            }
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}