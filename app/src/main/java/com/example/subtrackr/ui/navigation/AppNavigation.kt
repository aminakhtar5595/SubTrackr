package com.example.subtrackr.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.subtrackr.ui.screens.analysis.AnalysisScreen
import com.example.subtrackr.ui.screens.categories.CategoriesScreen
import com.example.subtrackr.ui.screens.categories.CategoryDetailScreen
import com.example.subtrackr.ui.screens.expense.ExpenseScreen
import com.example.subtrackr.ui.screens.home.HomeScreen
import com.example.subtrackr.ui.screens.search.SearchScreen
import com.example.subtrackr.ui.screens.splash.SplashScreen
import kotlinx.coroutines.delay

@Composable
fun AppNavigation(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = "splash", modifier = modifier) {
        composable("splash") { SplashScreen(navController) }
        composable("home") { HomeScreen(navController) }
        composable("search") { SearchScreen(navController) }
        composable("expense") { ExpenseScreen(navController) }
        composable("category") { CategoriesScreen(navController) }
        composable("analysis") { AnalysisScreen(navController) }
        composable("category_details") { CategoryDetailScreen(navController) }
    }

    LaunchedEffect(Unit) {
        delay(2000)
        navController.navigate("home") {
            popUpTo("splash") { inclusive = true }
        }
    }
}