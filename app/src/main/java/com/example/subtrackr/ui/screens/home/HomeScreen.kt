package com.example.subtrackr.ui.screens.home

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
fun HomeScreen() {
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

        Column (
            modifier = Modifier.padding(20.dp)
        ) {
            Text("Jun 21, Saturday", style = TextStyle(fontSize = 18.sp), color = Color(0xFF15433e), fontWeight = FontWeight.Medium)

            Spacer(modifier = Modifier.height(10.dp))
            Divider(
                color = Color(0xFF15433e),
                thickness = 1.dp
            )
            Spacer(modifier = Modifier.height(10.dp))

            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row {
                    Image(painter = painterResource(id = R.drawable.telephone_icon),
                        contentDescription = "Expense Icon")

                    Column (
                        modifier = Modifier.padding(start = 10.dp)
                    ) {
                        Text("Telephone", style = TextStyle(fontSize = 18.sp), color = Color(0xFF15433e), fontWeight = FontWeight.Medium)
                        Spacer(modifier = Modifier.height(5.dp))
                        Row (
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(painter = painterResource(id = R.drawable.cash_icon),
                                contentDescription = "Cash Icon", modifier = Modifier.size(30.dp))
                            Text("Cash", style = TextStyle(fontSize = 16.sp), color = Color(0xFF15433e), fontWeight = FontWeight.Medium, modifier = Modifier.padding(start = 5.dp))
                        }
                    }
                }
                Text("$5,910.00", style = TextStyle(fontSize = 18.sp), color = Color(0xFFa94943), fontWeight = FontWeight.SemiBold)
            }
        }
    }
}