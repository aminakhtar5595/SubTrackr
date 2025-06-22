package com.example.subtrackr.ui.screens.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
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
import androidx.compose.ui.window.Dialog
import com.example.subtrackr.R

@Composable
fun HomeScreen() {
    var showDialog by remember { mutableStateOf(false) }
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
    if (showDialog) {
        dialogView(
            dismiss = { showDialog = false },
        )
    }
}

@Composable
fun dialogView(dismiss: () -> Unit) {
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
                style = TextStyle(fontSize = 20.sp),
                color = Color(0xFF15433e),
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "Are you sure?",
                style = TextStyle(fontSize = 18.sp),
                color = Color(0xFF15433e),
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Button(
                    onClick = { dismiss() },
                    shape = RoundedCornerShape(5.dp),
                    contentPadding = PaddingValues(horizontal = 40.dp, vertical = 12.dp),
                    border = BorderStroke(2.dp, Color(0xFF15433e)),
                    colors = ButtonDefaults.buttonColors(contentColor = Color(0xFF15433e), containerColor = Color.Transparent),
                ) {
                    Text("NO", style = TextStyle(fontSize = 20.sp), fontWeight = FontWeight.Medium)
                }

                Button(
                    onClick = { dismiss() },
                    shape = RoundedCornerShape(5.dp),
                    contentPadding = PaddingValues(horizontal = 40.dp, vertical = 12.dp),
                    border = BorderStroke(2.dp, Color(0xFF15433e)),
                    colors = ButtonDefaults.buttonColors(contentColor = Color(0xFF15433e), containerColor = Color.Transparent),
                ) {
                    Text("YES", style = TextStyle(fontSize = 20.sp), fontWeight = FontWeight.Medium)
                }
            }
        }
    }
}
