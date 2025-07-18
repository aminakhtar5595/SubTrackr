package com.example.subtrackr.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.subtrackr.ui.model.BottomNavItem
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.subtrackr.R
import com.example.subtrackr.ui.theme.DarkBackground
import com.example.subtrackr.ui.theme.LightBackground
import com.example.subtrackr.ui.theme.PrimaryGreen

@Composable
fun BottomNavBar(navController: NavController) {
    val items = listOf(
        BottomNavItem("Home", "home", R.drawable.records_filled_icon, R.drawable.records_unfilled_icon),
        BottomNavItem("Analysis", "analysis", R.drawable.analysis_filled_icon, R.drawable.analysis_unfilled_icon),
        BottomNavItem("Category", "category", R.drawable.categories_filled_icon, R.drawable.categories_unfilled_icon)
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    if (items.any { it.route == currentRoute }) {
        Column {
            Divider(
                thickness = 1.dp,
                color = Color.LightGray
            )
            NavigationBar(
                containerColor = LightBackground
            ) {
                items.forEach { item ->
                    val selected = currentRoute == item.route

                    NavigationBarItem(
                        icon = {
                            Image(
                                painter = painterResource(
                                    id = if (selected) item.selectedIcon else item.unselectedIcon
                                ),
                                contentDescription = item.label
                            )
                        },
                        label = { Text(item.label, style = MaterialTheme.typography.bodyLarge.copy(color = PrimaryGreen)) },
                        selected = selected,
                        onClick = {
                            if (!selected) {
                                navController.navigate(item.route) {
                                    popUpTo("home") { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = LightBackground
                        ),
                    )

                }
            }
        }
    }
}