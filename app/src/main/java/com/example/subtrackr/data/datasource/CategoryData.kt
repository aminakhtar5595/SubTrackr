package com.example.subtrackr.data.datasource

import androidx.compose.runtime.mutableStateListOf
import com.example.subtrackr.data.model.Category
import com.example.subtrackr.data.model.CategoryType
import com.example.subtrackr.R

// Income categories hard coded data
val incomeData = CategoryType(
    name = "Income categories",
    type = "income",
    categories = mutableStateListOf(
        Category("Baby", R.drawable.baby_logo),
        Category("Bills", R.drawable.food_logo)
    )
)

// Expense categories hard coded data
val expenseData = CategoryType(
    name = "Expense categories",
    type = "expense",
    categories = mutableStateListOf(
        Category("Baby", R.drawable.baby_logo),
        Category("Beauty", R.drawable.beauty_logo),
        Category("Bills", R.drawable.bills_logo),
        Category("Clothing", R.drawable.clothing_logo),
        Category("Education", R.drawable.education_logo),
        Category("Electronics", R.drawable.electronics_logo),
        Category("Entertainment", R.drawable.entertainment_logo),
        Category("Family", R.drawable.transport_logo),
        Category("Food & Dining", R.drawable.food_logo),
        Category("Gifts & Donations", R.drawable.transport_logo),
        Category("Health", R.drawable.health_logo),
        Category("Housing / Rent", R.drawable.transport_logo),
        Category("Insurance", R.drawable.transport_logo),
        Category("Miscellaneous", R.drawable.transport_logo),
        Category("Shopping", R.drawable.shopping_logo),
        Category("Tax", R.drawable.transport_logo),
        Category("Transport", R.drawable.transport_logo),
        Category("Travel", R.drawable.transport_logo),
    )
)