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