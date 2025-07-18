package com.example.subtrackr.ui.model
import androidx.annotation.DrawableRes

data class BottomNavItem(
    val label: String,
    val route: String,
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int
)