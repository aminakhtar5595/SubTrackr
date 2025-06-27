package com.example.subtrackr.ui.screens.analysis

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
import androidx.compose.material3.Divider
import androidx.compose.material3.LinearProgressIndicator
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.subtrackr.R

@Composable
fun AnalysisScreen() {
    var progress by remember { mutableStateOf(0.2f) }
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFfffde8))
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
            Image(painter = painterResource(id = iconRes), contentDescription = "Expense Icon")

            Column(modifier = Modifier.width(235.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(label, style = TextStyle(fontSize = 18.sp), color = Color(0xFF15433e), fontWeight = FontWeight.Medium)
                    Text(amount, style = TextStyle(fontSize = 18.sp), color = Color(0xFFa94943), fontWeight = FontWeight.SemiBold)
                }

                Spacer(modifier = Modifier.height(10.dp))
                LinearProgressIndicator(
                    progress = progress,
                    modifier = Modifier.height(10.dp),
                    color = Color(0xFF15433e)
                )
            }
            Text(percentage, style = TextStyle(fontSize = 18.sp), color = Color(0xFF15433e), fontWeight = FontWeight.Medium)
        }
        Spacer(modifier = Modifier.height(10.dp))
        Divider(color = Color.LightGray, thickness = 1.dp)
    }
}
