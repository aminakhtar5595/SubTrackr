package com.example.subtrackr.data.local

import android.content.Context
import com.example.subtrackr.data.model.Expense
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import androidx.core.content.edit

object ExpenseStorage {
    private const val PREF_NAME = "expense_storage"
    private const val KEY_EXPENSES = "expenses"

    private val gson = Gson()

    // Save expenses
    fun saveExpense(context: Context, expense: Expense) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val existingJson = sharedPreferences.getString(KEY_EXPENSES, "[]")
        val type = object : TypeToken<MutableList<Expense>>() {}.type
        val expenseList: MutableList<Expense> = gson.fromJson(existingJson, type)

        expenseList.add(expense)
        val updatedJson = gson.toJson(expenseList)
        sharedPreferences.edit { putString(KEY_EXPENSES, updatedJson) }
    }

    // Get all expenses
    fun getExpenses(context: Context): List<Expense> {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val json = sharedPreferences.getString(KEY_EXPENSES, "[]")
        val type = object : TypeToken<List<Expense>>() {}.type
        return gson.fromJson(json, type)
    }

    // Clear all expenses
    fun clearExpenses(context: Context) {
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            .edit { remove(KEY_EXPENSES) }
    }

    // Delete expenses
    fun deleteExpense(context: Context, expenseToDelete: Expense) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val existingJson = sharedPreferences.getString(KEY_EXPENSES, "[]")
        val type = object : TypeToken<MutableList<Expense>>() {}.type
        val expenseList: MutableList<Expense> = gson.fromJson(existingJson, type)
        expenseList.removeIf { it.date == expenseToDelete.date && it.time == expenseToDelete.time && it.amount == expenseToDelete.amount }

        val updatedJson = gson.toJson(expenseList)
        sharedPreferences.edit { putString(KEY_EXPENSES, updatedJson) }
    }

    // Update expenses
    fun updateExpense(context: Context, updatedExpense: Expense) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val existingJson = sharedPreferences.getString(KEY_EXPENSES, "[]")
        val type = object : TypeToken<MutableList<Expense>>() {}.type
        val expenseList: MutableList<Expense> = gson.fromJson(existingJson, type)

        val index = expenseList.indexOfFirst { it.id == updatedExpense.id }
        if (index != -1) {
            expenseList[index] = updatedExpense
            val updatedJson = gson.toJson(expenseList)
            sharedPreferences.edit().putString(KEY_EXPENSES, updatedJson).apply()
        }
    }
}