package com.example.subtrackr.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.subtrackr.data.model.Category
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private val Context.expenseDataStore by preferencesDataStore("expense_data")

class ExpenseDataStoreManager(private val context: Context) {

    private val gson = Gson()
    private val EXPENSE_KEY = stringPreferencesKey("expense_categories")

    suspend fun saveExpenseCategories(categories: List<Category>) {
        val json = gson.toJson(categories)
        context.expenseDataStore.edit { prefs ->
            prefs[EXPENSE_KEY] = json
        }
    }


    suspend fun getExpenseCategories(): List<Category> {
        val json = context.expenseDataStore.data.map { prefs ->
            prefs[EXPENSE_KEY] ?: ""
        }.first()

        if (json.isEmpty()) return emptyList()

        val type = object : TypeToken<List<Category>>() {}.type
        return gson.fromJson(json, type)
    }
}