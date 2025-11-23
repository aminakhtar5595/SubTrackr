package com.example.subtrackr.ui.screens.analysis

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.subtrackr.data.local.ExpenseDataStoreManager
import com.example.subtrackr.data.local.ExpenseStorage
import com.example.subtrackr.data.model.Category
import com.example.subtrackr.data.model.CategoryAnalytics
import com.example.subtrackr.ui.components.FloatingButton
import com.example.subtrackr.ui.components.Header
import com.example.subtrackr.ui.theme.LightBackground
import com.example.subtrackr.ui.theme.LightGray
import com.example.subtrackr.ui.theme.PrimaryGreen
import com.example.subtrackr.ui.theme.PrimaryRed

@Composable
fun AnalysisScreen(navController: NavController) {
    val context = LocalContext.current
    val expenseManager = remember { ExpenseDataStoreManager(context) }

    val expenseCategories by produceState(initialValue = emptyList<Category>()) {
        value = expenseManager.getExpenseCategories()
    }
    val scrollState = rememberScrollState()
    var expenses by remember { mutableStateOf(ExpenseStorage.getExpenses(context)) }

    // Calculate total spent
    val totalAmount = expenses.sumOf { it.amount.toDoubleOrNull() ?: 0.0 }

    // Group expenses by category and calculate totals
    val categoryTotals: Map<String, Double> =
        expenses.groupBy { it.category }
            .mapValues { entry ->
                entry.value.sumOf { it.amount.toDoubleOrNull() ?: 0.0 }
            }

    // Convert to list sorted by highest spending
    val categoryBreakdown = expenseCategories.map { cat ->
        val totalForCategory = categoryTotals[cat.name] ?: 0.0
        val percentage = if (totalAmount == 0.0) 0.0 else (totalForCategory / totalAmount) * 100
        val progress = if (totalAmount == 0.0) 0f else (totalForCategory / totalAmount).toFloat()

        CategoryAnalytics(
            icon = cat.icon,
            name = cat.name,
            total = totalForCategory,
            progress = progress,
            percentage = percentage
        )
    }

    Log.d("AnalysisScreen", "expenses: $expenses")

    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = LightBackground)) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 20.dp)
                .verticalScroll(scrollState),
        ) {

            // First main section
            Header(onClickSearch = { navController.navigate("search") })

            Divider(color = LightGray, thickness = 3.dp)

            // Second main section
            if (expenses.isEmpty()) {
                Text(
                    "No analysis for this month",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = PrimaryGreen,
                        fontWeight = FontWeight.W500
                    ),
                    modifier = Modifier.padding(20.dp)
                )
                return
            }
            CategoryAmountSection(
                breakdown = categoryBreakdown,
                navController = navController
            )
        }

        // Align FAB to bottom-end
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp)
        ) {
            FloatingButton(onClick = {
                navController.navigate("expense_screen?expenseId=null")
            })
        }
    }
}

@SuppressLint("DefaultLocale")
@Composable
fun CategoryAmountSection(
    breakdown: List<CategoryAnalytics>,
    navController: NavController
) {
    breakdown.forEach { item ->
        Log.d("AnalysisScreen", "item: $item")
        if (item.total > 0) {
            ExpenseItem(
                iconRes = item.icon,
                label = item.name,
                amount = "-${String.format("%.2f", item.total)}",
                progress = item.progress,
                percentage = "${String.format("%.2f", item.percentage)}%",
                onClick = {
                    navController.navigate("category_details?categoryName=${item.name}&source=analysis")
                }

            )
        }
    }
}

@Composable
fun ExpenseItem(
    iconRes: Int,
    label: String,
    amount: String,
    progress: Float,
    percentage: String,
    onClick: () -> Unit
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .clickable { onClick() },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Image(painter = painterResource(id = iconRes), contentDescription = "$label Icon")

            Column(modifier = Modifier.width(235.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(label, style = MaterialTheme.typography.titleLarge.copy(color = PrimaryGreen, fontSize = 18.sp, fontWeight = FontWeight.W500))
                    Text(amount, style = MaterialTheme.typography.titleLarge.copy(color = PrimaryRed, fontSize = 18.sp, fontWeight = FontWeight.SemiBold))
                }

                Spacer(modifier = Modifier.height(10.dp))
                LinearProgressIndicator(
                    progress = progress,
                    modifier = Modifier.height(10.dp),
                    color = Color(0xFF15433e)
                )
            }
            Text(percentage, style = MaterialTheme.typography.titleLarge.copy(color = PrimaryGreen, fontSize = 18.sp, fontWeight = FontWeight.W500))
        }
        Spacer(modifier = Modifier.height(10.dp))
        Divider(color = Color.LightGray, thickness = 1.dp)
    }
}
