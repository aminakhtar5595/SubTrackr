package com.example.subtrackr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.subtrackr.ui.screens.home.HomeScreen
import com.example.subtrackr.ui.screens.search.SearchScreen
import com.example.subtrackr.ui.screens.splash.SplashScreen
import com.example.subtrackr.ui.theme.SubTrackrTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SearchScreen()
            }
        }
    }