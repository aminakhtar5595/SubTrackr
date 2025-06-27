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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.subtrackr.R

@Composable
fun CategoriesScreen() {
    val scrollState = rememberScrollState()
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFfffde8))
            .verticalScroll(scrollState)
            .padding(vertical = 20.dp),
    ) {
        Column (
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row {
                    Image(painter = painterResource(id = R.drawable.menu_icon),
                        contentDescription = "Menu Icon")

                    Spacer(modifier = Modifier.padding(end = 30.dp))
                    Image(painter = painterResource(id = R.drawable.app_icon),
                        contentDescription = "App Icon", modifier = Modifier.height(27.dp))
                }
                Image(painter = painterResource(id = R.drawable.search_icon),
                    contentDescription = "Search Icon", modifier = Modifier.size(30.dp))
            }

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp, bottom = 20.dp),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Spacer(modifier = Modifier.padding())
                Image(painter = painterResource(id = R.drawable.left_arrow_icon),
                    contentDescription = "Right Arrow Icon")

                Text("June, 2025", style = TextStyle(fontSize = 18.sp), color = Color(0xFF15433e), fontWeight = FontWeight.Medium)

                Image(painter = painterResource(id = R.drawable.right_arrow_icon),
                    contentDescription = "Right Arrow Icon")

                Image(painter = painterResource(id = R.drawable.filter_icon),
                    contentDescription = "Filter Icon")
            }

            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("EXPENSE", style = TextStyle(fontSize = 16.sp), color = Color(0xFF15433e), fontWeight = FontWeight.Medium)
                Text("$5,910.00", style = TextStyle(fontSize = 16.sp), color = Color(0xFFa94943), fontWeight = FontWeight.Medium)
            }
        }

        Divider(
            color = Color(0xFFf3f0dd),
            thickness = 3.dp
        )

        Column (
            modifier = Modifier.padding(vertical = 20.dp, horizontal = 10.dp)
        ) {
            Text(
                "Expense categories",
                style = TextStyle(fontSize = 20.sp),
                color = Color(0xFF15433e),
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(5.dp))
            Divider(color = Color(0xFF15433e), thickness = 1.5.dp)
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
            Spacer(modifier = Modifier.padding(start = 12.dp))
            Text(name, style = TextStyle(fontSize = 20.sp), color = Color(0xFF15433e), fontWeight = FontWeight.Medium)
        }

        Text("●●●", style = TextStyle(fontSize = 14.sp), color = Color(0xFF15433e))
    }
}