package com.example.subtrackr.ui.screens.expense

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.subtrackr.R
import com.example.subtrackr.data.model.Expense
import com.example.subtrackr.ui.components.AccountTag
import com.example.subtrackr.ui.components.ActionTag
import com.example.subtrackr.ui.theme.BorderGreen
import com.example.subtrackr.ui.theme.LightBackground
import com.example.subtrackr.ui.theme.PlaceholderGray
import com.example.subtrackr.ui.theme.PrimaryGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpenseScreen(navController: NavController) {
    var accountType by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var notesText by remember { mutableStateOf("") }
    var amountText by remember { mutableStateOf("0") }
    var date by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp, start = 10.dp, end = 10.dp),
    ) {
        // First section - header
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ActionTag(icon = Icons.Filled.Close, iconDescription = "Close Icon", title = "CANCEL", onClick = { navController.popBackStack() })
            ActionTag(icon = Icons.Filled.Check, iconDescription = "Save Icon", title = "SAVE",
                onClick = {
                    val expense = Expense(
                        accountType = "cash",
                        category = "Food",
                        note = notesText,
                        amount = amountText,
                        date = date,
                        time = time
                    )
                    Log.d("ExpenseScreen", "Add expense: $expense" )
                })
        }

        // Second section - expense
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ) {
            Image(painter = painterResource(id = R.drawable.expense_tick),
                contentDescription = "Expense Icon", modifier = Modifier.size(24.dp))
            Text("EXPENSE", style = MaterialTheme.typography.titleLarge.copy(color = PrimaryGreen, fontSize = 20.sp, fontWeight = FontWeight.Bold), modifier = Modifier.padding(start = 8.dp))
        }

        // Third section - account, category
        Row (
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            AccountTag(title= "Account", icon= R.drawable.expense_account, iconDescription= "Expense Account", modifier = Modifier.weight(1f))
            AccountTag(title= "Category", icon= R.drawable.expense_category, iconDescription= "Expense Category", modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(6.dp))

        // Fourth section - Add notes
        TextField(
            value = notesText,
            onValueChange = { notesText = it },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                textColor = Color.Gray,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                placeholderColor = Color.LightGray
            ),
            textStyle = TextStyle(fontSize = 20.sp, color = BorderGreen, fontWeight = FontWeight.SemiBold),
            placeholder = { Text("Add notes", style = MaterialTheme.typography.titleLarge.copy(color = PlaceholderGray, fontSize = 20.sp, fontWeight = FontWeight.W500)) },
            modifier = Modifier
                .border(
                    width = 2.dp,
                    color = BorderGreen,
                    shape = RoundedCornerShape(6.dp)
                )
                .fillMaxWidth()
                .background(color = LightBackground)
                .height(105.dp)
        )

        Spacer(modifier = Modifier.height(6.dp))

        // Fifth section - Amount
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 2.dp,
                    color = BorderGreen,
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(amountText,style = MaterialTheme.typography.titleLarge.copy(color = PrimaryGreen, fontSize = 50.sp, fontWeight = FontWeight.W500), modifier = Modifier.padding(end = 15.dp))
                Image(
                    painter = painterResource(id = R.drawable.expense_cancel),
                    contentDescription = "Cancel Icon",
                    modifier = Modifier.size(30.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(4.dp))

        // Sixth section - Calculator
        CalculatorRow(listOf("+", "7", "8", "9"))
        Spacer(modifier = Modifier.height(4.dp))
        CalculatorRow(listOf("-", "4", "5", "6"))
        Spacer(modifier = Modifier.height(4.dp))
        CalculatorRow(listOf("x", "1", "2", "3"))
        Spacer(modifier = Modifier.height(4.dp))
        CalculatorRow(listOf("/", "0", ".", "="))

        Row (
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("June 20, 2025", style = MaterialTheme.typography.titleLarge.copy(color = BorderGreen, fontSize = 20.sp, fontWeight = FontWeight.W500))
            Text("|", style = MaterialTheme.typography.titleLarge.copy(color = BorderGreen, fontSize = 30.sp, fontWeight = FontWeight.ExtraBold))
            Text("9:49 PM", style = MaterialTheme.typography.titleLarge.copy(color = BorderGreen, fontSize = 20.sp, fontWeight = FontWeight.W500))
        }
    }
}

@Composable
fun CalculatorRow(buttons: List<String>) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        buttons.forEach { text ->
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(60.dp)
                    .background(
                        color = if (text in listOf("+", "-", "x", "/", "=")) BorderGreen else Color.White,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .border(2.dp, BorderGreen, RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text,
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = if (text in listOf("+", "-", "x", "/", "=")) Color.White else BorderGreen,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.W500
                    )
                )
            }
        }
    }
}
