package com.example.subtrackr.ui.screens.categories
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.List
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.subtrackr.data.local.ExpenseStorage
import com.example.subtrackr.ui.theme.BorderGreen
import com.example.subtrackr.ui.theme.DarkBackground
import com.example.subtrackr.ui.theme.LightBackground
import com.example.subtrackr.ui.theme.LightGray
import com.example.subtrackr.ui.theme.PrimaryGreen
import com.example.subtrackr.ui.theme.PrimaryRed
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun CategoryDetailScreen(navController: NavController, categoryName: String) {
    var noDetails by remember { mutableStateOf(false) }
    val context = LocalContext.current
    var expenses by remember { mutableStateOf(ExpenseStorage.getExpenses(context).sortedByDescending { it.date }) }

    val filteredExpenses = remember(expenses, categoryName) {
        expenses.filter { it.category == categoryName }
    }

    val groupedExpenses = filteredExpenses.groupBy { it.date }

    Column (
        modifier = Modifier
            .background(color = LightBackground)
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.Clear,
                contentDescription = "Cross Icon",
                modifier = Modifier
                    .size(30.dp)
                    .clickable { navController.popBackStack() },
                tint = PrimaryGreen,
            )

            Column {
                Text("Category details", style = MaterialTheme.typography.titleLarge.copy(color = PrimaryGreen, fontWeight = FontWeight.W500), modifier = Modifier.padding(bottom = 5.dp))
                Text("Records: All time", style = MaterialTheme.typography.titleMedium.copy(color = BorderGreen))
            }
        }
        Divider(color = LightGray, thickness = 3.dp)

        Column (
            modifier = Modifier.padding(20.dp)
        ) {
            Row (
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                Image(
                    painter = painterResource(id = filteredExpenses[0].categoryIcon),
                    contentDescription = "$categoryName Icon",
                )

                Column {
                    Text(categoryName, style = MaterialTheme.typography.titleLarge.copy(color = PrimaryGreen, fontWeight = FontWeight.W500), modifier = Modifier.padding(bottom = 5.dp))
                    Text("Expense category", style = MaterialTheme.typography.titleMedium.copy(color = BorderGreen))
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .border(2.dp, color = LightGray, RoundedCornerShape(6.dp))
                    .background(color = DarkBackground)
                    .padding(12.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "Info Icon",
                    modifier = Modifier.size(30.dp),
                    tint = BorderGreen,
                )
                Text("You can see Monthly, weekly, or daily statistics in the Analysis section.", style = MaterialTheme.typography.titleLarge.copy(color = BorderGreen, fontSize = 18.sp, fontWeight = FontWeight.W500), modifier = Modifier.padding(start = 15.dp))
            }

            Spacer(modifier = Modifier.height(20.dp))

            if (noDetails) {
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("No record in this category",style = MaterialTheme.typography.titleLarge.copy(color = PrimaryGreen))
                }
            } else {
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Total ${filteredExpenses.size} records in this category", style = MaterialTheme.typography.titleLarge.copy(color = PrimaryGreen, fontWeight = FontWeight.W500, fontSize = 20.sp), modifier = Modifier
                        .weight(1f)
                        .padding(end = 15.dp))

                    Row (
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.List,
                            contentDescription = "Filter Icon",
                            modifier = Modifier.size(25.dp),
                            tint = BorderGreen,
                        )
                        Text("NEW TO OLD", style = MaterialTheme.typography.titleLarge.copy(color = BorderGreen, fontWeight = FontWeight.W500, fontSize = 20.sp))
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))
                groupedExpenses.forEach { (date, expenseList) ->
                    val formattedDate = try {
                        val inputFormat = SimpleDateFormat("MMM d, yyyy", Locale.getDefault())
                        val parsedDate = inputFormat.parse(date)
                        val displayFormat = SimpleDateFormat("MMM d, EEEE", Locale.getDefault())
                        displayFormat.format(parsedDate!!)
                    } catch (e: Exception) {
                        date
                    }

                    Text(formattedDate, style = MaterialTheme.typography.titleLarge.copy(color = PrimaryGreen, fontWeight = FontWeight.W500, fontSize = 18.sp), modifier = Modifier.padding(bottom = 10.dp))
                    Divider(color = PrimaryGreen, thickness = 1.dp)
                    Spacer(modifier = Modifier.height(10.dp))
                    expenseList.forEach { item ->
                        ExpenseInfo(
                            time = item.time,
                            accountType = item.accountType,
                            amount = item.amount,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ExpenseInfo(time: String, accountType: String, amount: String) {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 15.dp)
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text("● $time", style = MaterialTheme.typography.titleLarge.copy(color = BorderGreen, fontWeight = FontWeight.W500, fontSize = 20.sp), modifier = Modifier.padding(end = 10.dp))
            Text(accountType, style = MaterialTheme.typography.titleLarge.copy(color = PrimaryGreen, fontWeight = FontWeight.SemiBold, fontSize = 20.sp))
        }
        Text("-$$amount", style = MaterialTheme.typography.titleLarge.copy(color = PrimaryRed, fontWeight = FontWeight.W500, fontSize = 20.sp))
    }
}