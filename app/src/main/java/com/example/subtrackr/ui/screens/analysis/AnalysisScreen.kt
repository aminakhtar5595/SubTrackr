package com.example.subtrackr.ui.screens.analysis

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.subtrackr.R
import com.example.subtrackr.ui.screens.home.LargeExample
import com.example.subtrackr.ui.theme.LightBackground
import com.example.subtrackr.ui.theme.LightGray
import com.example.subtrackr.ui.theme.PrimaryGreen
import com.example.subtrackr.ui.theme.PrimaryRed

@Composable
fun AnalysisScreen(navController: NavController) {
    var progress by remember { mutableStateOf(0.2f) }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = LightBackground)) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 20.dp),
        ) {

            // First main section
            Column(
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {

                // First section - Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Row(
                        verticalAlignment = Alignment.Bottom,
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Menu Icon",
                            modifier = Modifier.size(30.dp)
                        )

                        Spacer(modifier = Modifier.width(30.dp))
                        Image(
                            painter = painterResource(id = R.drawable.app_icon),
                            contentDescription = "App Icon",
                            modifier = Modifier.height(25.dp)
                        )
                    }

                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search Icon",
                        modifier = Modifier.size(30.dp),
                        tint = PrimaryGreen
                    )
                }

                // Second section - Date
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 30.dp, bottom = 20.dp),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Spacer(modifier = Modifier.width(8.dp))
                    Image(
                        painter = painterResource(id = R.drawable.left_arrow_icon),
                        contentDescription = "Left Arrow Icon"
                    )
                    Text(
                        "June, 2025",
                        style = MaterialTheme.typography.titleLarge.copy(
                            color = PrimaryGreen,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.W500
                        )
                    )
                    Image(
                        painter = painterResource(id = R.drawable.right_arrow_icon),
                        contentDescription = "Right Arrow Icon"
                    )
                    Image(
                        painter = painterResource(id = R.drawable.filter_icon),
                        contentDescription = "Filter Icon"
                    )
                }

                // Third section - Expense amount
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "EXPENSE",
                        style = MaterialTheme.typography.titleMedium.copy(color = PrimaryGreen)
                    )
                    Text(
                        "$5,910.00",
                        style = MaterialTheme.typography.titleMedium.copy(color = PrimaryRed)
                    )
                }
            }

            Divider(color = LightGray, thickness = 3.dp)

            // Second main section
            ExpenseItem(
                iconRes = R.drawable.bills_icon,
                label = "Bills",
                amount = "-$3,800.00",
                progress = 0.7f,
                percentage = "64.30%"
            )

            ExpenseItem(
                iconRes = R.drawable.telephone_icon,
                label = "Telephone",
                amount = "-$810.00",
                progress = 0.2f,
                percentage = "13.71%"
            )

            ExpenseItem(
                iconRes = R.drawable.food_icon,
                label = "Food",
                amount = "-$900.00",
                progress = 0.3f,
                percentage = "15.54%"
            )
        }

        // Align FAB to bottom-end
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp)
        ) {
            LargeExample(onClick = {
                // Example: navController.navigate("expense")
            })
        }
    }
}

@Composable
fun ExpenseItem(
    iconRes: Int,
    label: String,
    amount: String,
    progress: Float,
    percentage: String
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
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
