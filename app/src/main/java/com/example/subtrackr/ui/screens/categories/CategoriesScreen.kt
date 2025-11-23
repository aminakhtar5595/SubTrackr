package com.example.subtrackr.ui.screens.categories

import android.content.Context
import android.util.Log
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.subtrackr.data.datasource.expenseData
import com.example.subtrackr.data.datasource.incomeData
import com.example.subtrackr.data.local.ExpenseDataStoreManager
import com.example.subtrackr.data.local.IncomeDataStoreManager
import com.example.subtrackr.data.model.Category
import com.example.subtrackr.ui.components.ButtonWithIcon
import com.example.subtrackr.ui.components.Header
import com.example.subtrackr.ui.theme.BorderGreen
import com.example.subtrackr.ui.theme.DarkBackground
import com.example.subtrackr.ui.theme.LightBackground
import com.example.subtrackr.ui.theme.LightGray
import com.example.subtrackr.ui.theme.PlaceholderGray
import com.example.subtrackr.ui.theme.PrimaryGreen

@Composable
fun CategoriesScreen(navController: NavController, context: Context = LocalContext.current) {
    var newCategoryDialog by remember { mutableStateOf(false) }
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
    if (newCategoryDialog) {
        addNewCategoryDialog(
            dismiss = { newCategoryDialog = false },
        )
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
            image = category.icon,
            onClick = { navController.navigate("category_details?categoryName=${category.name}&source=category") }
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun addNewCategoryDialog(dismiss: () -> Unit) {
    Dialog(onDismissRequest = { dismiss() }) {
        Column(
            modifier = Modifier
                .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                .padding(horizontal = 32.dp, vertical = 24.dp)
                .width(320.dp)
                .wrapContentHeight(),
//            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Add new category",
                style = MaterialTheme.typography.headlineSmall.copy(color = PrimaryGreen, fontWeight = FontWeight.W500)
            )
            Spacer(modifier = Modifier.height(16.dp))

            Row {
                Text(
                    "Type:",
                    style = MaterialTheme.typography.titleMedium.copy(color = PrimaryGreen, fontWeight = FontWeight.W400)
                )

                Text(
                    "Income",
                    style = MaterialTheme.typography.titleMedium.copy(color = PrimaryGreen, fontWeight = FontWeight.W400),
                    modifier = Modifier.padding(start = 10.dp, end = 30.dp)
                )

                Text(
                    "Expense",
                    style = MaterialTheme.typography.titleMedium.copy(color = PrimaryGreen, fontWeight = FontWeight.W400)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))
            Row (
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Name",
                    style = MaterialTheme.typography.titleMedium.copy(color = PrimaryGreen, fontWeight = FontWeight.W400),
                    modifier = Modifier.padding(end = 10.dp)
                )

                TextField(
                    value = "",
                    onValueChange = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 2.dp,
                            color = BorderGreen,
                            shape = RoundedCornerShape(8.dp)
                        ),
//                    colors = TextFieldDefaults.textFieldColors(
//                        containerColor = DarkBackground,
//                        textColor = Color.Gray,
//                        focusedIndicatorColor = Color.Transparent,
//                        unfocusedIndicatorColor = Color.Transparent,
//                        placeholderColor = Color.LightGray
//                    ),
                    textStyle = TextStyle(
                        fontSize = 20.sp,
                        color = BorderGreen,
                        fontWeight = FontWeight.SemiBold
                    ),
                    placeholder = {
                        Text(
                            "Untitled",
                            style = MaterialTheme.typography.titleLarge.copy(
                                color = PlaceholderGray,
                                fontSize = 20.sp
                            )
                        )
                    },
                )


            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Button(
                    onClick = { dismiss() },
                    shape = RoundedCornerShape(5.dp),
//                    contentPadding = PaddingValues(horizontal = 40.dp, vertical = 12.dp),
                    border = BorderStroke(2.dp, PrimaryGreen),
                    colors = ButtonDefaults.buttonColors(contentColor = PrimaryGreen, containerColor = Color.Transparent),
                ) {
                    Text("CANCEL",
                        style = MaterialTheme.typography.titleSmall.copy(color = PrimaryGreen, fontWeight = FontWeight.W500, fontSize = 20.sp)
                    )
                }

                Button(
                    onClick = { dismiss() },
                    shape = RoundedCornerShape(5.dp),
//                    contentPadding = PaddingValues(horizontal = 40.dp, vertical = 12.dp),
                    border = BorderStroke(2.dp, PrimaryGreen),
                    colors = ButtonDefaults.buttonColors(contentColor = PrimaryGreen, containerColor = Color.Transparent),
                ) {
                    Text("SAVE",
                        style = MaterialTheme.typography.titleSmall.copy(color = PrimaryGreen, fontWeight = FontWeight.W500, fontSize = 20.sp)
                    )
                }
            }
        }
    }
}