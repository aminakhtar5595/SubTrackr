package com.example.subtrackr.data.model

data class Expense(
    val paymentType: String = "expense",
    val accountType: String,
    val category: String,
    val note: String,
    val amount: String,
    val date: String,
    val time: String
)