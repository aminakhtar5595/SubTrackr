package com.example.subtrackr.data.datasource

import androidx.compose.runtime.mutableStateListOf
import com.example.subtrackr.data.model.Category
import com.example.subtrackr.data.model.CategoryType
import com.example.subtrackr.R

val incomeData = CategoryType(
    name = "Income categories",
    type = "income",
    categories = mutableStateListOf(
        Category("Baby", R.drawable.baby_icon),
        Category("Bills", R.drawable.food_icon)
    )
)

val expenseData = CategoryType(
    name = "Expense categories",
    type = "expense",
    categories = mutableStateListOf(
        Category("Food", R.drawable.food_icon),
        Category("Transport", R.drawable.transportation_icon),
        Category("Shopping", R.drawable.shopping_icon),
        Category("Health", R.drawable.health_icon),
        Category("Bills", R.drawable.bills_icon),
        Category("Entertainment", R.drawable.entertainment_icon),
        Category("Beauty", R.drawable.beauty_icon),
        Category("Baby", R.drawable.baby_icon),
        Category("Car", R.drawable.car_icon),
        Category("Clothing", R.drawable.clothing_icon),
        Category("Education", R.drawable.education_icon),
        Category("Electronics", R.drawable.electronics_icon)
    )
)