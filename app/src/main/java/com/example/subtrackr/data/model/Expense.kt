package com.example.subtrackr.data.model

import java.util.UUID

data class Expense(
    val id: String = UUID.randomUUID().toString(),
    val paymentType: String = "expense",
    val accountType: String,
    val category: String,
    val note: String,
    val amount: String,
    val date: String,
    val time: String,
    val accountIcon: Int,
    val categoryIcon: Int
)