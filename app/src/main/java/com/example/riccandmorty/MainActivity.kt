package com.example.riccandmorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.riccandmorty.presentation.ui.theme.RiccAndMortyTheme
import timber.log.Timber

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RiccAndMortyTheme {
                Timber.plant(Timber.DebugTree())
            }
        }
    }
}
