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

private val Context.incomeDataStore by preferencesDataStore("income_data")

class IncomeDataStoreManager(private val context: Context) {

    private val gson = Gson()
    private val INCOME_KEY = stringPreferencesKey("income_categories")

    // Save income categories
    suspend fun saveIncomeCategories(categories: List<Category>) {
        val json = gson.toJson(categories)
        context.incomeDataStore.edit { prefs ->
            prefs[INCOME_KEY] = json
        }
    }

    // Get income categories
    suspend fun getIncomeCategories(): List<Category> {
        val json = context.incomeDataStore.data.map { prefs ->
            prefs[INCOME_KEY] ?: ""
        }.first()

        if (json.isEmpty()) return emptyList()

        val type = object : TypeToken<List<Category>>() {}.type
        return gson.fromJson(json, type)
    }
}