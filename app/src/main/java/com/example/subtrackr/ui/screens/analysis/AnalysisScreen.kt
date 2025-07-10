package com.example.subtrackr.ui.screens.analysis

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
import androidx.compose.material3.Divider
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
import com.example.subtrackr.ui.components.FloatingButton
import com.example.subtrackr.ui.components.Header
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
            Header(onClickSearch = { navController.navigate("search") })

            Divider(color = LightGray, thickness = 3.dp)

            // Second main section
            ExpenseItem(
                iconRes = R.drawable.bills_icon,
                label = "Bills",
                amount = "-$3,800.00",
                progress = 0.7f,
                percentage = "64.30%",
                onClick = { navController.navigate("category_details") }
            )

            ExpenseItem(
                iconRes = R.drawable.telephone_icon,
                label = "Telephone",
                amount = "-$810.00",
                progress = 0.2f,
                percentage = "13.71%",
                onClick = { navController.navigate("category_details") }
            )

            ExpenseItem(
                iconRes = R.drawable.food_icon,
                label = "Food",
                amount = "-$900.00",
                progress = 0.3f,
                percentage = "15.54%",
                onClick = { navController.navigate("category_details") }
            )
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
