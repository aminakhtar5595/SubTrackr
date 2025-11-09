package com.example.subtrackr.ui.screens.expense

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.SheetState
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.ui.platform.LocalContext
import com.example.subtrackr.data.datasource.expenseData
import com.example.subtrackr.data.local.ExpenseStorage
import com.example.subtrackr.ui.components.AccountTypeTag
import com.example.subtrackr.ui.components.ButtonWithIcon
import com.example.subtrackr.ui.components.CategoryTag
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpenseScreen(navController: NavController) {
    var notesText by remember { mutableStateOf("") }
    var amountText by remember { mutableStateOf("0") }
    var expression by remember { mutableStateOf("") }
    val context = LocalContext.current

    val dateFormat = remember { SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()) }

    val currentDate = remember { dateFormat.format(Date()) }
    var selectedDate by remember { mutableStateOf(currentDate) }
    var showDatePicker by remember { mutableStateOf(false) }

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()
    var showSheet by remember { mutableStateOf(false) }
    var showCategorySheet by remember { mutableStateOf(false) }

    var selectAccountTitle by remember {
        mutableStateOf("Account")
    }

    var selectedAccountIcon by remember {
        mutableStateOf(R.drawable.expense_account)
    }

    var selectCategoryTitle by remember {
        mutableStateOf("Category")
    }

    var selectedCategoryIcon by remember {
        mutableStateOf(R.drawable.expense_category)
    }

    // Function to handle calculator clicks
    fun onButtonClick(value: String) {
        when (value) {
            "=" -> {
                val result = calculateAmount(expression)
                amountText = if (result.isNaN()) "Error" else result.toString()
                expression = result.toString()
            }
            else -> {
                expression += value
                amountText = expression
            }
        }
    }

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
            ActionTag(icon = Icons.Filled.Close, iconDescription = "Close Icon", title = "CANCEL",
                onClick = {
                    ExpenseStorage.clearExpenses(context)
                    navController.popBackStack()
                })
            ActionTag(icon = Icons.Filled.Check, iconDescription = "Save Icon", title = "SAVE",
                onClick = {
                    val expense = Expense(
                        accountType = selectAccountTitle,
                        category = selectCategoryTitle,
                        note = notesText,
                        amount = amountText,
                        date = selectedDate,
                        time = SimpleDateFormat("h:mm a", Locale.getDefault()).format(Date()),
                        accountIcon = selectedAccountIcon,
                        categoryIcon = selectedCategoryIcon
                    )
                    ExpenseStorage.saveExpense(context, expense)
                    Log.d("ExpenseScreen", "Expense Added: $expense")
                    navController.popBackStack()
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
            AccountTag(label= "Account", title= selectAccountTitle, icon= selectedAccountIcon, iconDescription= "Expense Account", modifier = Modifier.weight(1f), onClick = {
                showSheet = true
                scope.launch { sheetState.show() }
            })
            AccountTag(label= "Category", title= selectCategoryTitle, icon= selectedCategoryIcon, iconDescription= "Expense Category", modifier = Modifier.weight(1f), onClick = {
                showCategorySheet = true
                scope.launch { sheetState.show() }
            })
        }

        Spacer(modifier = Modifier.height(6.dp))

        // Fourth section - Add notes
        TextField(
            value = notesText,
            onValueChange = { notesText = it },
//            colors = TextFieldDefaults.textFieldColors(
//                containerColor = Color.Transparent,
//                textColor = Color.Gray,
//                focusedIndicatorColor = Color.Transparent,
//                unfocusedIndicatorColor = Color.Transparent,
//                placeholderColor = Color.LightGray
//            ),
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
                    .clickable {
                        expression = ""
                        amountText = "0"
                    }
                )
            }
        }
        Spacer(modifier = Modifier.height(4.dp))

        // Sixth section - Calculator
        CalculatorRow(listOf("+", "7", "8", "9")) { onButtonClick(it) }
        Spacer(modifier = Modifier.height(4.dp))
        CalculatorRow(listOf("-", "4", "5", "6")) { onButtonClick(it) }
        Spacer(modifier = Modifier.height(4.dp))
        CalculatorRow(listOf("x", "1", "2", "3")) { onButtonClick(it) }
        Spacer(modifier = Modifier.height(4.dp))
        CalculatorRow(listOf("/", "0", ".", "=")) { onButtonClick(it) }

        Row (
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxSize()
            .clickable { showDatePicker = true },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(selectedDate, style = MaterialTheme.typography.titleLarge.copy(color = BorderGreen, fontSize = 20.sp, fontWeight = FontWeight.W500))
            Text("|", style = MaterialTheme.typography.titleLarge.copy(color = BorderGreen, fontSize = 30.sp, fontWeight = FontWeight.ExtraBold))
            Text(SimpleDateFormat("h:mm a", Locale.getDefault()).format(Date()), style = MaterialTheme.typography.titleLarge.copy(color = BorderGreen, fontSize = 20.sp, fontWeight = FontWeight.W500))
        }
    }
    SelectAccountTypeModal(
        showSheet = showSheet,
        sheetState = sheetState,
        onDismissRequest = { showSheet = false },
        onAccountSelected = { title, icon ->
            selectAccountTitle = title
            selectedAccountIcon = icon
            showSheet = false
        }
    )

    SelectCategoryModal(
        showSheet = showCategorySheet,
        sheetState = sheetState,
        onDismissRequest = { showCategorySheet = false },
        onCategorySelected = { title, icon ->
            selectCategoryTitle = title
            selectedCategoryIcon = icon
            showCategorySheet = false
        }
    )

    if (showDatePicker) {
        val initialMillis = remember(selectedDate) {
            try {
                dateFormat.parse(selectedDate)?.time
            } catch (e: Exception) {
                null
            }
        }
        val timeZoneOffset = TimeZone.getDefault().getOffset(Date().time)
        val adjustedMillis = initialMillis?.plus(timeZoneOffset)

        val datePickerState = rememberDatePickerState(
            initialSelectedDateMillis = adjustedMillis
        )

        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(onClick = {
                    val millis = datePickerState.selectedDateMillis
                    if (millis != null) {
                        // Re-adjust UTC back to local timezone
                        val localMillis = millis - timeZoneOffset
                        selectedDate = dateFormat.format(Date(localMillis))
                    }
                    showDatePicker = false
                }) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text("Cancel")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

}

@Composable
fun CalculatorRow(buttons: List<String>, onButtonClick: (String) -> Unit) {
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
                        color = if (text in listOf(
                                "+",
                                "-",
                                "x",
                                "/",
                                "="
                            )
                        ) BorderGreen else Color.White,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .border(2.dp, BorderGreen, RoundedCornerShape(8.dp))
                    .clickable { onButtonClick(text) },
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectAccountTypeModal(
    showSheet: Boolean,
    sheetState: SheetState,
    onDismissRequest: () -> Unit,
    onAccountSelected: (String, Int) -> Unit
) {
    if (showSheet) {
        ModalBottomSheet(
            onDismissRequest = onDismissRequest,
            sheetState = sheetState,
            dragHandle = null,
            tonalElevation = 0.dp,
            scrimColor = Color.Black.copy(alpha = 0.5f),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Select an account",
                    style = MaterialTheme.typography.headlineSmall.copy(color = PrimaryGreen, fontWeight = FontWeight.W500, fontSize = 22.sp,)
                )
                AccountTypeTag(icon = R.drawable.card_icon, title = "Card", amount = "3,410.00", onPress = { onAccountSelected("Card", R.drawable.card_icon) })
                AccountTypeTag(icon = R.drawable.cash_icon, title = "Cash", amount = "2,500.00",onPress = { onAccountSelected("Cash", R.drawable.cash_icon) })
                Spacer(modifier = Modifier.height(20.dp))
                ButtonWithIcon(title = "ADD NEW ACCOUNT", onClick = { })
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectCategoryModal(
    showSheet: Boolean,
    sheetState: SheetState,
    onDismissRequest: () -> Unit,
    onCategorySelected: (String, Int) -> Unit
) {
    val categoryData = expenseData.categories
    if (showSheet) {
        ModalBottomSheet(
            onDismissRequest = onDismissRequest,
            sheetState = sheetState,
            dragHandle = null,
            tonalElevation = 0.dp,
            scrimColor = Color.Black.copy(alpha = 0.5f),
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Select a category",
                    style = MaterialTheme.typography.headlineSmall.copy(color = PrimaryGreen, fontWeight = FontWeight.W500, fontSize = 22.sp,)
                )
                Spacer(modifier = Modifier.height(20.dp))
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    items(categoryData.size) { index ->
                        val category = categoryData[index]
                        CategoryTag(icon = category.icon, title = category.name, onPress = { onCategorySelected(category.name, category.icon) })
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                ButtonWithIcon(onClick = { })
            }
        }
    }
}

fun calculateAmount(expression: String): Double {
    return try {
        val cleanExpr = expression.replace("x", "*")

        val parser = object {
            var pos = -1
            var ch = 0

            fun nextChar() {
                ch = if (++pos < cleanExpr.length) cleanExpr[pos].code else -1
            }

            fun eat(charToEat: Int): Boolean {
                while (ch == ' '.code) nextChar()
                if (ch == charToEat) {
                    nextChar()
                    return true
                }
                return false
            }

            fun parse(): Double {
                nextChar()
                val x = parseExpression()
                if (pos < cleanExpr.length) throw RuntimeException("Unexpected: ${ch.toChar()}")
                return x
            }

            fun parseExpression(): Double {
                var x = parseTerm()
                while (true) {
                    when {
                        eat('+'.code) -> x += parseTerm()
                        eat('-'.code) -> x -= parseTerm()
                        else -> return x
                    }
                }
            }

            fun parseTerm(): Double {
                var x = parseFactor()
                while (true) {
                    when {
                        eat('*'.code) -> x *= parseFactor()
                        eat('/'.code) -> x /= parseFactor()
                        else -> return x
                    }
                }
            }

            fun parseFactor(): Double {
                if (eat('+'.code)) return parseFactor()
                if (eat('-'.code)) return -parseFactor()

                var x: Double
                val startPos = pos
                when {
                    eat('('.code) -> {
                        x = parseExpression()
                        eat(')'.code)
                    }
                    ch in '0'.code..'9'.code || ch == '.'.code -> {
                        while (ch in '0'.code..'9'.code || ch == '.'.code) nextChar()
                        x = cleanExpr.substring(startPos, pos).toDouble()
                    }
                    else -> throw RuntimeException("Unexpected: ${ch.toChar()}")
                }
                return x
            }
        }

        parser.parse()
    } catch (e: Exception) {
        Double.NaN
    }
}
