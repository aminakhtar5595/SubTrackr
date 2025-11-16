package com.example.subtrackr.data.model

data class Category(
    val name: String,
    val icon: Int
)

data class CategoryType (
    val name: String,
    val type: String,
    val categories: MutableList<Category>
)

data class CategoryAnalytics(
    val icon: Int,
    val name: String,
    val total: Double,
    val progress: Float,
    val percentage: Double
)