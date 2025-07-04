package com.example.subtrackr.ui.screens.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.subtrackr.R

@Composable
fun HomeScreen(navController: NavController) {
    var deleteDialog by remember { mutableStateOf(false) }
    var detailsDialog by remember { mutableStateOf(false) }
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFfffde8))
            .padding(vertical = 20.dp),
    ) {
        Column (
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {

        // First section
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

                Spacer(modifier = Modifier.padding(start = 30.dp))
                Image(painter = painterResource(id = R.drawable.app_icon),
                    contentDescription = "App Icon", modifier = Modifier.height(25.dp))
            }

            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Search Icon",
                modifier = Modifier.size(30.dp),
                tint = Color(0xFF15433e)
            )
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
                contentDescription = "Left Arrow Icon")

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
    if (deleteDialog) {
        deleteDialogView(
            dismiss = { deleteDialog = false },
        )
    }

    if (detailsDialog) {
        detailsDialogView(
            dismiss = { detailsDialog = false },
        )
    }
}

@Composable
fun deleteDialogView(dismiss: () -> Unit) {
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

@Composable
fun detailsDialogView(dismiss: () -> Unit) {
    Dialog(onDismissRequest = { dismiss() }) {
        Column(
            modifier = Modifier
                .background(color = Color(0xFFc62827), shape = RoundedCornerShape(10.dp))
                .width(320.dp)
                .wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Column (
                modifier = Modifier.padding(top = 20.dp, bottom = 10.dp, start = 20.dp, end = 20.dp)
            ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 30.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(painter = painterResource(id = R.drawable.cross_icon),
                    contentDescription = "Menu Icon")

                Row {
                    Image(painter = painterResource(id = R.drawable.delete_icon),
                        contentDescription = "Menu Icon")

                    Spacer(modifier = Modifier.padding(start = 20.dp))
                    Image(painter = painterResource(id = R.drawable.edit_icon),
                        contentDescription = "Menu Icon")
                }
            }

            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    "EXPENSE",
                    style = TextStyle(fontSize = 18.sp),
                    color = Color.White,
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    "-$1,100.00",
                    style = TextStyle(fontSize = 30.sp),
                    color = Color.White,
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                "Jun 21, 2025 9:35 AM",
                style = TextStyle(fontSize = 14.sp),
                color = Color.White,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Right
            )
            }

            Column (
                modifier = Modifier
                    .background(color = Color(0xFFfffde8))
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 30.dp, start = 20.dp, end = 20.dp)
            ) {
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        "Account",
                        style = TextStyle(fontSize = 20.sp),
                        color = Color(0xFF15433e),
                    )

                    Spacer(modifier = Modifier.padding(start = 10.dp))
                    Row (
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .border(
                                width = 2.dp,
                                color = Color(0xFF689383),
                                shape = RoundedCornerShape(6.dp)
                            )
                            .padding(vertical = 10.dp, horizontal = 12.dp)
                    ) {
                        Image(painter = painterResource(id = R.drawable.card_icon),
                            contentDescription = "Card Icon", modifier = Modifier.size(35.dp))
                        Spacer(modifier = Modifier.padding(start = 5.dp))
                        Text("Card", style = TextStyle(fontSize = 18.sp), color = Color(0xFF15433e))
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        "Category",
                        style = TextStyle(fontSize = 20.sp),
                        color = Color(0xFF15433e),
                    )

                    Spacer(modifier = Modifier.padding(start = 10.dp))
                    Row (
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .border(
                                width = 2.dp,
                                color = Color(0xFF689383),
                                shape = RoundedCornerShape(6.dp)
                            )
                            .padding(vertical = 10.dp, horizontal = 12.dp)
                    ) {
                        Image(painter = painterResource(id = R.drawable.bills_icon),
                            contentDescription = "Card Icon", modifier = Modifier.size(35.dp))
                        Spacer(modifier = Modifier.padding(start = 5.dp))
                        Text("Bills", style = TextStyle(fontSize = 18.sp), color = Color(0xFF15433e))
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    "Electricity Bill",
                    style = TextStyle(fontSize = 20.sp),
                    color = Color(0xFF15433e),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
