package com.example.subtrackr.ui.screens.expense

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.subtrackr.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpenseScreen() {
    var notesText by remember { mutableStateOf("") }
    var amountText by remember { mutableStateOf("0") }
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFfffde8))
            .padding(vertical = 20.dp, horizontal = 10.dp),
    ) {
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row (
                verticalAlignment = Alignment.Bottom
            ) {
                Image(painter = painterResource(id = R.drawable.cancel_icon),
                    contentDescription = "Cancel Icon", modifier = Modifier.size(22.dp))
                Spacer(modifier = Modifier.padding(start = 8.dp))
                Text("CANCEL", style = TextStyle(fontSize = 18.sp), color = Color(0xFF679384), fontWeight = FontWeight.Bold)
            }

            Row (
                verticalAlignment = Alignment.Bottom
            ) {
                Image(painter = painterResource(id = R.drawable.save_icon),
                    contentDescription = "Cancel Icon", modifier = Modifier.size(22.dp))
                Spacer(modifier = Modifier.padding(start = 8.dp))
                Text("SAVE", style = TextStyle(fontSize = 18.sp), color = Color(0xFF679384), fontWeight = FontWeight.Bold)
            }
        }

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ) {
            Image(painter = painterResource(id = R.drawable.save_icon),
                contentDescription = "Cancel Icon", modifier = Modifier.size(24.dp))
            Spacer(modifier = Modifier.padding(start = 8.dp))
            Text("EXPENSE", style = TextStyle(fontSize = 20.sp), color = Color(0xFF679384), fontWeight = FontWeight.Bold)
        }

        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text("Account", style = TextStyle(fontSize = 18.sp), color = Color(0xFF679384), fontWeight = FontWeight.Medium)
                Spacer(modifier = Modifier.padding(bottom = 5.dp))
                Row (
                    modifier = Modifier
                        .border(
                            width = 2.dp,
                            color = Color(0xFF689383),
                            shape = RoundedCornerShape(6.dp)
                        )
                        .padding(vertical = 15.dp, horizontal = 30.dp)
                ) {
                    Image(painter = painterResource(id = R.drawable.save_icon),
                        contentDescription = "Cancel Icon", modifier = Modifier.size(24.dp))
                    Spacer(modifier = Modifier.padding(start = 8.dp))
                    Text("Account", style = TextStyle(fontSize = 20.sp), color = Color(0xFF679384), fontWeight = FontWeight.Medium)
                }
            }

            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text("Category", style = TextStyle(fontSize = 18.sp), color = Color(0xFF679384), fontWeight = FontWeight.Medium)
                Spacer(modifier = Modifier.padding(bottom = 5.dp))
                Row (
                    modifier = Modifier
                        .border(
                            width = 2.dp,
                            color = Color(0xFF689383),
                            shape = RoundedCornerShape(6.dp)
                        )
                        .padding(vertical = 15.dp, horizontal = 30.dp)
                ) {
                    Image(painter = painterResource(id = R.drawable.save_icon),
                        contentDescription = "Cancel Icon", modifier = Modifier.size(24.dp))
                    Spacer(modifier = Modifier.padding(start = 8.dp))
                    Text("Category", style = TextStyle(fontSize = 20.sp), color = Color(0xFF679384), fontWeight = FontWeight.Medium)
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = notesText,
            onValueChange = { notesText = it },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                textColor = Color.Gray,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                placeholderColor = Color.LightGray
            ),
            textStyle = TextStyle(
                fontSize = 20.sp, color = Color(0xFF689383), fontWeight = FontWeight.SemiBold),
            placeholder = { Text("Add notes", style = TextStyle(fontSize = 20.sp), color = Color(0xFF9c9778), fontWeight = FontWeight.Medium) },
            modifier = Modifier
                .border(
                    width = 2.dp,
                    color = Color(0xFF689383),
                    shape = RoundedCornerShape(6.dp)
                )
                .fillMaxWidth()
                .background(color = Color(0xFFfffcd6))
                .height(100.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 2.dp,
                    color = Color(0xFF689383),
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(amountText, style = TextStyle(fontSize = 50.sp), color = Color(0xFF084c3c), fontWeight = FontWeight.Medium)
                Spacer(modifier = Modifier.padding(start = 8.dp))
                Image(
                    painter = painterResource(id = R.drawable.search_green_icon),
                    contentDescription = "Search Icon",
                    modifier = Modifier.size(20.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(60.dp)
                    .background(color = Color(0xFF689383), shape = RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "+",
                    style = TextStyle(fontSize = 25.sp),
                    color = Color.White,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center
                )
            }

            listOf("7", "8", "9").forEach { text ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(60.dp)
                        .background(color = Color.White, shape = RoundedCornerShape(8.dp))
                        .border(
                            width = 2.dp,
                            color = Color(0xFF689383),
                            shape = RoundedCornerShape(8.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text,
                        style = TextStyle(fontSize = 25.sp),
                        color = Color(0xFF689383),
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(60.dp)
                    .background(color = Color(0xFF689383), shape = RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "-",
                    style = TextStyle(fontSize = 25.sp),
                    color = Color.White,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center
                )
            }

            listOf("4", "5", "6").forEach { text ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(60.dp)
                        .background(color = Color.White, shape = RoundedCornerShape(8.dp))
                        .border(
                            width = 2.dp,
                            color = Color(0xFF689383),
                            shape = RoundedCornerShape(8.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text,
                        style = TextStyle(fontSize = 25.sp),
                        color = Color(0xFF689383),
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(60.dp)
                    .background(color = Color(0xFF689383), shape = RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "x",
                    style = TextStyle(fontSize = 25.sp),
                    color = Color.White,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center
                )
            }

            listOf("1", "2", "3").forEach { text ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(60.dp)
                        .background(color = Color.White, shape = RoundedCornerShape(8.dp))
                        .border(
                            width = 2.dp,
                            color = Color(0xFF689383),
                            shape = RoundedCornerShape(8.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text,
                        style = TextStyle(fontSize = 25.sp),
                        color = Color(0xFF689383),
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(60.dp)
                    .background(color = Color(0xFF689383), shape = RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "/",
                    style = TextStyle(fontSize = 25.sp),
                    color = Color.White,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center
                )
            }

            listOf("0", ".").forEach { text ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(60.dp)
                        .background(color = Color.White, shape = RoundedCornerShape(8.dp))
                        .border(
                            width = 2.dp,
                            color = Color(0xFF689383),
                            shape = RoundedCornerShape(8.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text,
                        style = TextStyle(fontSize = 25.sp),
                        color = Color(0xFF689383),
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center
                    )
                }
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(60.dp)
                    .background(color = Color(0xFF689383), shape = RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "=",
                    style = TextStyle(fontSize = 25.sp),
                    color = Color.White,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}