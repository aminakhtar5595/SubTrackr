package com.example.subtrackr.ui.screens.home

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.subtrackr.R
import com.example.subtrackr.data.local.ExpenseStorage
import com.example.subtrackr.data.model.Expense
import com.example.subtrackr.ui.components.FloatingButton
import com.example.subtrackr.ui.components.Header
import com.example.subtrackr.ui.theme.BackgroundRed
import com.example.subtrackr.ui.theme.BorderGreen
import com.example.subtrackr.ui.theme.LightBackground
import com.example.subtrackr.ui.theme.LightGray
import com.example.subtrackr.ui.theme.LightGreen
import com.example.subtrackr.ui.theme.PrimaryGreen
import com.example.subtrackr.ui.theme.PrimaryRed
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun HomeScreen(navController: NavController) {
    var deleteDialog by remember { mutableStateOf(false) }
    var detailsDialog by remember { mutableStateOf(false) }
    var optionsDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current
    var expenses by remember { mutableStateOf(ExpenseStorage.getExpenses(context)
        .sortedByDescending { it.date }) }
    Log.d("HomeScreen", "GET Expenses: $expenses")
    var selectedExpense by remember { mutableStateOf<Expense?>(null) }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = LightBackground)) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 20.dp),
        ) {

            // First main section
            Header(onClickSearch = { navController.navigate("search") })

            Divider(color = LightGray, thickness = 3.dp)

            // Second main section
            ExpenseSection(
                expenses,
                onExpenseClick = { expense ->
                selectedExpense = expense
                detailsDialog = true
            })
        }

        // Align FAB to bottom-end
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp)
        ) {
            FloatingButton(onClick = {
                 navController.navigate("expense")
            })
        }
    }

    if (deleteDialog && selectedExpense != null) {
        DeleteDialogView(
            dismiss = { deleteDialog = false },
            onConfirmDelete = {
                ExpenseStorage.deleteExpense(context, selectedExpense!!)
                expenses = ExpenseStorage.getExpenses(context)
                    .sortedByDescending { it.date }
                deleteDialog = false
            }
        )
    }

    if (detailsDialog && selectedExpense != null) {
        DetailsDialogView(
            expense = selectedExpense!!,
            dismiss = { detailsDialog = false },
            delete = {
                detailsDialog = false
                deleteDialog = true
            },
            edit = {
                detailsDialog = false
                navController.navigate("expense")
            }
        )
    }


    if (optionsDialog) {
        OptionsDialogView(
            dismiss = { optionsDialog = false },
        )
    }
}

@Composable
fun DeleteDialogView(dismiss: () -> Unit, onConfirmDelete: () -> Unit) {
    Dialog(onDismissRequest = { dismiss() }) {
        Column(
            modifier = Modifier
                .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                .padding(horizontal = 32.dp, vertical = 24.dp)
                .width(320.dp)
                .wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Delete this record?",
                style = MaterialTheme.typography.headlineSmall.copy(color = PrimaryGreen, fontWeight = FontWeight.W500)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "Are you sure?",
                style = MaterialTheme.typography.titleLarge.copy(color = PrimaryGreen, fontWeight = FontWeight.W400)
            )
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Button(
                    onClick = { dismiss() },
                    shape = RoundedCornerShape(5.dp),
                    contentPadding = PaddingValues(horizontal = 40.dp, vertical = 12.dp),
                    border = BorderStroke(2.dp, PrimaryGreen),
                    colors = ButtonDefaults.buttonColors(contentColor = PrimaryGreen, containerColor = Color.Transparent),
                ) {
                    Text("NO",
                        style = MaterialTheme.typography.titleSmall.copy(color = PrimaryGreen, fontWeight = FontWeight.W500, fontSize = 20.sp)
                    )
                }

                Button(
                    onClick = { onConfirmDelete() },
                    shape = RoundedCornerShape(5.dp),
                    contentPadding = PaddingValues(horizontal = 40.dp, vertical = 12.dp),
                    border = BorderStroke(2.dp, PrimaryGreen),
                    colors = ButtonDefaults.buttonColors(contentColor = PrimaryGreen, containerColor = Color.Transparent),
                ) {
                    Text("YES",
                        style = MaterialTheme.typography.titleSmall.copy(color = PrimaryGreen, fontWeight = FontWeight.W500, fontSize = 20.sp)
                    )
                }
            }
        }
    }
}

@Composable
fun DetailsDialogView(expense: Expense, dismiss: () -> Unit, delete: () -> Unit, edit: () -> Unit) {
    Dialog(onDismissRequest = { dismiss() }) {
        Column(
            modifier = Modifier
                .background(color = BackgroundRed, shape = RoundedCornerShape(10.dp))
                .width(320.dp)
                .wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Column (
                modifier = Modifier.padding(top = 20.dp, bottom = 10.dp, start = 20.dp, end = 20.dp)
            ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 30.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(painter = painterResource(id = R.drawable.cross_icon),
                    contentDescription = "Cross Icon", Modifier.clickable { dismiss() })

                Row {
                    Image(painter = painterResource(id = R.drawable.delete_icon),
                        contentDescription = "Delete Icon", Modifier.clickable { delete() })

                    Spacer(modifier = Modifier.width(20.dp))
                    Image(painter = painterResource(id = R.drawable.edit_icon),
                        contentDescription = "Edit Icon", Modifier.clickable { edit() })
                }
            }

            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {

                Text("EXPENSE",style = MaterialTheme.typography.titleLarge.copy(color = Color.White, fontSize = 18.sp))
                Spacer(modifier = Modifier.height(15.dp))
                Text("-$${expense.amount}",style = MaterialTheme.typography.headlineLarge.copy(color = Color.White))
            }

            Spacer(modifier = Modifier.height(20.dp))
            Text("${expense.date} ${expense.time}",style = MaterialTheme.typography.bodyMedium.copy(color = Color.White, textAlign = TextAlign.Right), modifier = Modifier.fillMaxWidth())
            }

            Column (
                modifier = Modifier
                    .background(color = LightBackground)
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 30.dp, start = 20.dp, end = 20.dp)
            ) {
                InfoTag(title = "Account", icon = expense.accountIcon, label = expense.accountType)
                Spacer(modifier = Modifier.height(10.dp))
                InfoTag(title = "Category", icon = expense.categoryIcon, label = expense.category)
                Spacer(modifier = Modifier.height(20.dp))
                Text(expense.note, style = MaterialTheme.typography.titleLarge.copy(color = LightGreen, textAlign = TextAlign.Center), modifier = Modifier.fillMaxWidth())
            }
        }
    }
}

@Composable
fun InfoTag(title: String, icon: Int, label: String) {
    Row (
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(title,style = MaterialTheme.typography.titleLarge.copy(color = PrimaryGreen, fontWeight = FontWeight.W500), modifier = Modifier.padding(end = 15.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .border(2.dp, color = BorderGreen, RoundedCornerShape(6.dp))
                .padding(vertical = 10.dp, horizontal = 12.dp)
        ) {
            Image(painter = painterResource(icon), contentDescription = label, modifier = Modifier.size(35.dp))
            Text(label, style = MaterialTheme.typography.bodyLarge.copy(color = PrimaryGreen, fontWeight = FontWeight.W500, fontSize = 18.sp), modifier = Modifier.padding(start = 5.dp))
        }
    }
}

@Composable
fun OptionsDialogView(dismiss: () -> Unit) {
    Dialog(onDismissRequest = { dismiss() }) {
        Column(
            modifier = Modifier
                .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                .padding(horizontal = 32.dp, vertical = 24.dp)
                .width(320.dp)
                .wrapContentHeight()
        ) {
            Text("Display options", style = MaterialTheme.typography.headlineSmall.copy(color = PrimaryGreen, fontWeight = FontWeight.W500, textAlign = TextAlign.Center), modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(30.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                Text("View mode:", style = MaterialTheme.typography.titleLarge.copy(color = PrimaryGreen, fontSize = 18.sp, fontWeight = FontWeight.W500), modifier = Modifier.weight(2f))

                Column(verticalArrangement = Arrangement.spacedBy(15.dp), modifier = Modifier.weight(2f)) {
                    Text("DAILY", style = MaterialTheme.typography.titleLarge.copy(color = BorderGreen, fontSize = 18.sp, fontWeight = FontWeight.W500))
                    Text("WEEKLY", style = MaterialTheme.typography.titleLarge.copy(color = BorderGreen, fontSize = 18.sp, fontWeight = FontWeight.W500))
                    Text("MONTHLY", style = MaterialTheme.typography.titleLarge.copy(color = BorderGreen, fontSize = 18.sp, fontWeight = FontWeight.W500))
                    Text("3 MONTHS", style = MaterialTheme.typography.titleLarge.copy(color = BorderGreen, fontSize = 18.sp, fontWeight = FontWeight.W500))
                    Text("6 MONTHS", style = MaterialTheme.typography.titleLarge.copy(color = BorderGreen, fontSize = 18.sp, fontWeight = FontWeight.W500))
                    Text("YEARLY", style = MaterialTheme.typography.titleLarge.copy(color = BorderGreen, fontSize = 18.sp, fontWeight = FontWeight.W500))
                }
            }

            Spacer(modifier = Modifier.height(30.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                Text("Show total:", style = MaterialTheme.typography.titleLarge.copy(color = PrimaryGreen, fontSize = 18.sp, fontWeight = FontWeight.W500), modifier = Modifier.weight(2f))

                Column(verticalArrangement = Arrangement.spacedBy(15.dp), modifier = Modifier.weight(2f)) {
                    Text("YES", style = MaterialTheme.typography.titleLarge.copy(color = BorderGreen, fontSize = 18.sp, fontWeight = FontWeight.W500))
                    Text("NO", style = MaterialTheme.typography.titleLarge.copy(color = BorderGreen, fontSize = 18.sp, fontWeight = FontWeight.W500))
                }
            }

            Spacer(modifier = Modifier.height(30.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                Text("Carry over:", style = MaterialTheme.typography.titleLarge.copy(color = PrimaryGreen, fontSize = 18.sp, fontWeight = FontWeight.W500), modifier = Modifier.weight(2f))

                Column(verticalArrangement = Arrangement.spacedBy(15.dp), modifier = Modifier.weight(2f)) {
                    Text("ON", style = MaterialTheme.typography.titleLarge.copy(color = BorderGreen, fontSize = 18.sp, fontWeight = FontWeight.W500))
                    Text("OFF", style = MaterialTheme.typography.titleLarge.copy(color = BorderGreen, fontSize = 18.sp, fontWeight = FontWeight.W500))
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "Menu Icon",
                    modifier = Modifier.size(30.dp),
                    tint = BorderGreen,
                )

                Text("With Carry over enabled, monthly surplus will be added to the next month.", style = MaterialTheme.typography.titleLarge.copy(color = BorderGreen, fontSize = 18.sp, fontWeight = FontWeight.W500), modifier = Modifier.padding(start = 15.dp))
            }
        }
    }
}

@Composable
fun ExpenseSection(expenses: List<Expense>, onExpenseClick: (Expense) -> Unit) {
    val scrollState = rememberScrollState()
    if (expenses.isEmpty()) {
        Text(
            "No expenses found",
            style = MaterialTheme.typography.bodyLarge.copy(
                color = PrimaryGreen,
                fontWeight = FontWeight.W500
            ),
            modifier = Modifier.padding(20.dp)
        )
        return
    }

    val groupedExpenses = expenses.groupBy { it.date }

    Column(modifier = Modifier.padding(vertical = 20.dp).verticalScroll(scrollState)) {
        groupedExpenses.forEach { (date, expenseList) ->
            val formattedDate = try {
                val inputFormat = SimpleDateFormat("MMM d, yyyy", Locale.getDefault())
                val parsedDate = inputFormat.parse(date)
                val displayFormat = SimpleDateFormat("MMM d, EEEE", Locale.getDefault())
                displayFormat.format(parsedDate!!)
            } catch (e: Exception) {
                date
            }

            Text(
                formattedDate,
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = PrimaryGreen,
                    fontWeight = FontWeight.W500
                ), modifier = Modifier.padding(horizontal = 15.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))
            Divider(color = PrimaryGreen, thickness = 1.dp)
            Spacer(modifier = Modifier.height(10.dp))

            expenseList.forEach { expense ->
                ExpenseDetails(
                    categoryIcon = expense.categoryIcon,
                    categoryName = expense.category,
                    accountIcon = expense.accountIcon,
                    accountType = expense.accountType,
                    expenseAmount = expense.amount,
                    onClick = { onExpenseClick(expense) }
                )
                Spacer(modifier = Modifier.height(10.dp))
                Divider(color = LightGray, thickness = 1.dp)
                Spacer(modifier = Modifier.height(10.dp))
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}


@Composable
fun ExpenseDetails(categoryIcon: Int, categoryName: String, accountIcon: Int, accountType: String, expenseAmount: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp)
        .clickable { onClick() },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row {
            Image(
                painter = painterResource(id = categoryIcon),
                contentDescription = "Expense Icon"
            )

            Column(
                modifier = Modifier.padding(start = 10.dp)
            ) {
                Text(
                    categoryName,
                    style = MaterialTheme.typography.titleSmall.copy(
                        color = PrimaryGreen,
                        fontWeight = FontWeight.W500,
                        fontSize = 20.sp
                    )
                )
                Spacer(modifier = Modifier.height(5.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = accountIcon),
                        contentDescription = "$accountType Icon",
                        modifier = Modifier.size(30.dp)
                    )
                    Text(
                        accountType,
                        style = MaterialTheme.typography.titleMedium.copy(color = LightGreen),
                        modifier = Modifier.padding(start = 5.dp)
                    )
                }
            }
        }
        Text(
            "-$$expenseAmount",
            style = MaterialTheme.typography.titleMedium.copy(
                color = PrimaryRed,
                fontWeight = FontWeight.W500,
                fontSize = 20.sp
            )
        )
    }
}