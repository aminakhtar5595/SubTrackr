package com.example.subtrackr.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
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
    NavHost(navController = navController, startDestination = "home", modifier = modifier) {
        composable("splash") { SplashScreen() }
        composable("home") { HomeScreen(navController) }
        composable("search") { SearchScreen() }
        composable(
            route = "expense_screen?expenseId={expenseId}",
            arguments = listOf(
                navArgument("expenseId") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            )
        ) {
            val expenseId = it.arguments?.getString("expenseId")
            ExpenseScreen(navController, expenseId)
        }
        composable("category") { CategoriesScreen(navController) }
        composable("analysis") { AnalysisScreen(navController) }
        composable(
            route = "category_details?categoryName={categoryName}"
        ) { backStackEntry ->
            val categoryName = backStackEntry.arguments?.getString("categoryName")
            if (categoryName != null) {
                CategoryDetailScreen(navController, categoryName)
            }
        }
    }

    LaunchedEffect(Unit) {
        delay(2000)
        navController.navigate("home") {
            popUpTo("splash") { inclusive = true }
        }
    }
}