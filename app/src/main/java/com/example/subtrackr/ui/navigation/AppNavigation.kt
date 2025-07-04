package com.example.subtrackr.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.subtrackr.ui.screens.home.HomeScreen
import com.example.subtrackr.ui.screens.splash.SplashScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("splash") { SplashScreen(navController) }
        composable("home") { HomeScreen(navController) }
    }
}
