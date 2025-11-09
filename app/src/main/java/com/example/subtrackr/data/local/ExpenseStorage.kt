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

    fun saveExpense(context: Context, expense: Expense) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val existingJson = sharedPreferences.getString(KEY_EXPENSES, "[]")
        val type = object : TypeToken<MutableList<Expense>>() {}.type
        val expenseList: MutableList<Expense> = gson.fromJson(existingJson, type)

        expenseList.add(expense)
        val updatedJson = gson.toJson(expenseList)

        sharedPreferences.edit { putString(KEY_EXPENSES, updatedJson) }
    }

    fun getExpenses(context: Context): List<Expense> {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val json = sharedPreferences.getString(KEY_EXPENSES, "[]")
        val type = object : TypeToken<List<Expense>>() {}.type
        return gson.fromJson(json, type)
    }

    fun clearExpenses(context: Context) {
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            .edit { remove(KEY_EXPENSES) }
    }
}
