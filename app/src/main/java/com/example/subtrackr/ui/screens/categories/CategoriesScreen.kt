package com.example.subtrackr.ui.screens.categories

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.subtrackr.data.datasource.expenseData
import com.example.subtrackr.data.datasource.incomeData
import com.example.subtrackr.data.local.ExpenseDataStoreManager
import com.example.subtrackr.data.local.IncomeDataStoreManager
import com.example.subtrackr.data.model.Category
import com.example.subtrackr.ui.components.ButtonWithIcon
import com.example.subtrackr.ui.components.Header
import com.example.subtrackr.ui.theme.LightBackground
import com.example.subtrackr.ui.theme.LightGray
import com.example.subtrackr.ui.theme.PrimaryGreen

@Composable
fun CategoriesScreen(navController: NavController, context: Context = LocalContext.current) {
    val scrollState = rememberScrollState()
    val expenseDataList = expenseData
    val incomeDataList = incomeData

    val incomeManager = remember { IncomeDataStoreManager(context) }
    val expenseManager = remember { ExpenseDataStoreManager(context) }

    var incomeCategories by remember { mutableStateOf<List<Category>>(emptyList()) }
    var expenseCategories by remember { mutableStateOf<List<Category>>(emptyList()) }

    LaunchedEffect(Unit) {
        // Load from DataStore
        val savedIncome = incomeManager.getIncomeCategories()
        val savedExpense = expenseManager.getExpenseCategories()

        Log.d("CategoryScreen", "Saved income: $savedIncome")


        incomeCategories = savedIncome.ifEmpty {
            incomeManager.saveIncomeCategories(incomeDataList.categories)
            incomeDataList.categories
        }

        expenseCategories = savedExpense.ifEmpty {
            expenseManager.saveExpenseCategories(expenseDataList.categories)
            expenseDataList.categories
        }
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(color = LightBackground)
            .verticalScroll(scrollState)
            .padding(vertical = 20.dp),
    ) {
        Header(onClickSearch = { navController.navigate("search") })
        Divider(color = LightGray, thickness = 3.dp)

        Column (
            modifier = Modifier.padding(vertical = 20.dp, horizontal = 10.dp),
        ) {

            CategorySection(
                title = incomeDataList.name,
                categories = incomeCategories,
                navController = navController
            )
            Spacer(modifier = Modifier.height(20.dp))
            CategorySection(
                title = expenseDataList.name,
                categories = expenseCategories,
                navController = navController
            )
            Spacer(modifier = Modifier.height(20.dp))
            Column (
                Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ButtonWithIcon(onClick = { })
            }
        }
    }
}

@Composable
fun CategorySection(title: String, categories: List<Category>, navController: NavController) {
    Text(title,style = MaterialTheme.typography.titleLarge.copy(color = PrimaryGreen, fontSize = 20.sp, fontWeight = FontWeight.W500))
    Spacer(modifier = Modifier.height(5.dp))
    Divider(color = PrimaryGreen, thickness = 1.5.dp)

    categories.forEach { category ->
        ExpenseCategory(
            name = category.name,
            image = category.icon, // you can later match by category name
//            onClick = { navController.navigate("category_details/${category.id}") }
            onClick = {  }
        )
    }
}

@Composable
fun ExpenseCategory(name: String, image: Int, onClick: () -> Unit) {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 5.dp)
            .clickable { onClick() },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = image),
                contentDescription = "Expense Icon"
            )
            Text(
                text = name,
                style = MaterialTheme.typography.titleLarge.copy(
                    color = PrimaryGreen,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W500
                ),
                modifier = Modifier.padding(start = 12.dp)
            )
        }
        Text("●●●", style = MaterialTheme.typography.titleMedium.copy(color = PrimaryGreen))
    }
}
