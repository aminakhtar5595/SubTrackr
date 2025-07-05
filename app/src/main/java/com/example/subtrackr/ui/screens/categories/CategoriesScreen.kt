package com.example.subtrackr.ui.screens.categories

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.subtrackr.R
import com.example.subtrackr.ui.theme.LightBackground
import com.example.subtrackr.ui.theme.LightGray
import com.example.subtrackr.ui.theme.PrimaryGreen
import com.example.subtrackr.ui.theme.PrimaryRed

@Composable
fun CategoriesScreen(navController: NavController) {
    val scrollState = rememberScrollState()
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(color = LightBackground)
            .verticalScroll(scrollState)
            .padding(vertical = 20.dp),
    ) {
        Column (
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {

            // First section - Header
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Row (
                    verticalAlignment = Alignment.Bottom,
                ) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = "Menu Icon",
                        modifier = Modifier.size(30.dp)
                    )

                    Spacer(modifier = Modifier.width(30.dp))
                    Image(painter = painterResource(id = R.drawable.app_icon), contentDescription = "App Icon", modifier = Modifier.height(25.dp))
                }

                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search Icon",
                    modifier = Modifier.size(30.dp),
                    tint = PrimaryGreen
                )
            }

            // Second section - Date
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp, bottom = 20.dp),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Spacer(modifier = Modifier.width(8.dp))
                Image(painter = painterResource(id = R.drawable.left_arrow_icon), contentDescription = "Left Arrow Icon")
                Text("June, 2025",style = MaterialTheme.typography.titleLarge.copy(color = PrimaryGreen, fontSize = 20.sp, fontWeight = FontWeight.W500))
                Image(painter = painterResource(id = R.drawable.right_arrow_icon), contentDescription = "Right Arrow Icon")
                Image(painter = painterResource(id = R.drawable.filter_icon), contentDescription = "Filter Icon")
            }

            // Third section - Expense amount
            Column (
                modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("EXPENSE",style = MaterialTheme.typography.titleMedium.copy(color = PrimaryGreen))
                Text("$5,910.00",style = MaterialTheme.typography.titleMedium.copy(color = PrimaryRed))
            }
        }

        Divider(color = LightGray, thickness = 3.dp)
        // Fourth section - Categories section
        Column (
            modifier = Modifier.padding(vertical = 20.dp, horizontal = 10.dp)
        ) {
            Text("Expense categories",style = MaterialTheme.typography.titleLarge.copy(color = PrimaryGreen, fontSize = 20.sp, fontWeight = FontWeight.W500))
            Spacer(modifier = Modifier.height(5.dp))
            Divider(color = PrimaryGreen, thickness = 1.5.dp)
            listOf(
                ExpenseCategory("Food", R.drawable.food_icon),
                ExpenseCategory("Transport", R.drawable.transportation_icon),
                ExpenseCategory("Shopping", R.drawable.shopping_icon),
                ExpenseCategory("Health", R.drawable.health_icon),
                ExpenseCategory("Bills", R.drawable.bills_icon),
                ExpenseCategory("Entertainment", R.drawable.entertainment_icon),
                ExpenseCategory("Beauty", R.drawable.beauty_icon),
                ExpenseCategory("Baby", R.drawable.baby_icon),
                ExpenseCategory("Car", R.drawable.car_icon),
                ExpenseCategory("Clothing", R.drawable.clothing_icon),
                ExpenseCategory("Education", R.drawable.education_icon),
                ExpenseCategory("Electronics", R.drawable.electronics_icon),
            )
        }
    }
}

@Composable
fun ExpenseCategory(name: String, image: Int) {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp, horizontal = 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(painter = painterResource(id = image), contentDescription = "Expense Icon")
            Text(name,style = MaterialTheme.typography.titleLarge.copy(color = PrimaryGreen, fontSize = 20.sp, fontWeight = FontWeight.W500), modifier = Modifier.padding(start = 12.dp))
        }
        Text("●●●",style = MaterialTheme.typography.titleMedium.copy(color = PrimaryGreen))
    }
}