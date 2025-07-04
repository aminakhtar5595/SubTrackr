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
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.subtrackr.R
import com.example.subtrackr.ui.theme.BackgroundRed
import com.example.subtrackr.ui.theme.BorderGreen
import com.example.subtrackr.ui.theme.LightBackground
import com.example.subtrackr.ui.theme.LightGray
import com.example.subtrackr.ui.theme.LightGreen
import com.example.subtrackr.ui.theme.PrimaryGreen
import com.example.subtrackr.ui.theme.PrimaryRed

@Composable
fun HomeScreen(navController: NavController) {
    var deleteDialog by remember { mutableStateOf(false) }
    var detailsDialog by remember { mutableStateOf(false) }
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(color = LightBackground)
            .padding(vertical = 20.dp),
    ) {

        // First main section
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

                Spacer(modifier = Modifier.padding(start = 30.dp))
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
            Spacer(modifier = Modifier.width(16.dp))
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

        Column (
            modifier = Modifier.padding(20.dp)
        ) {
            Text("Jun 21, Saturday",style = MaterialTheme.typography.bodyLarge.copy(color = PrimaryGreen, fontWeight = FontWeight.W500))
            Spacer(modifier = Modifier.height(10.dp))
            Divider(color = PrimaryGreen, thickness = 1.dp)
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
                        Text("Telephone",style = MaterialTheme.typography.titleSmall.copy(color = PrimaryGreen, fontWeight = FontWeight.W500, fontSize = 20.sp))
                        Spacer(modifier = Modifier.height(5.dp))
                        Row (
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(painter = painterResource(id = R.drawable.cash_icon),
                                contentDescription = "Cash Icon", modifier = Modifier.size(30.dp))
                            Spacer(modifier = Modifier.padding(start = 5.dp))
                            Text("Cash",style = MaterialTheme.typography.titleMedium.copy(color = LightGreen))
                        }
                    }
                }
                Text("-$5,910.00",style = MaterialTheme.typography.titleMedium.copy(color = PrimaryRed, fontWeight = FontWeight.W500, fontSize = 20.sp))
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
                style = MaterialTheme.typography.headlineSmall.copy(color = PrimaryGreen, fontWeight = FontWeight.W500)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "Are you sure?",
                style = MaterialTheme.typography.titleLarge.copy(color = PrimaryGreen, fontWeight = FontWeight.W400)
            )
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Button(
                    onClick = { dismiss() },
                    shape = RoundedCornerShape(5.dp),
                    contentPadding = PaddingValues(horizontal = 40.dp, vertical = 12.dp),
                    border = BorderStroke(2.dp, PrimaryGreen),
                    colors = ButtonDefaults.buttonColors(contentColor = PrimaryGreen, containerColor = Color.Transparent),
                ) {
                    Text("NO",
                        style = MaterialTheme.typography.titleSmall.copy(color = PrimaryGreen, fontWeight = FontWeight.W500, fontSize = 20.sp)
                    )
                }

                Button(
                    onClick = { dismiss() },
                    shape = RoundedCornerShape(5.dp),
                    contentPadding = PaddingValues(horizontal = 40.dp, vertical = 12.dp),
                    border = BorderStroke(2.dp, PrimaryGreen),
                    colors = ButtonDefaults.buttonColors(contentColor = PrimaryGreen, containerColor = Color.Transparent),
                ) {
                    Text("YES",
                        style = MaterialTheme.typography.titleSmall.copy(color = PrimaryGreen, fontWeight = FontWeight.W500, fontSize = 20.sp)
                    )
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
                .background(color = BackgroundRed, shape = RoundedCornerShape(10.dp))
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
                    contentDescription = "Cross Icon")

                Row {
                    Image(painter = painterResource(id = R.drawable.delete_icon),
                        contentDescription = "Delete Icon")

                    Spacer(modifier = Modifier.padding(start = 20.dp))
                    Image(painter = painterResource(id = R.drawable.edit_icon),
                        contentDescription = "Edit Icon")
                }
            }

            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {

                Text("EXPENSE",style = MaterialTheme.typography.titleLarge.copy(color = Color.White, fontSize = 18.sp))
                Spacer(modifier = Modifier.height(15.dp))
                Text("-$1,100.00",style = MaterialTheme.typography.headlineLarge.copy(color = Color.White))
            }

            Spacer(modifier = Modifier.height(20.dp))
            Text("Jun 21, 2025 9:35 AM",style = MaterialTheme.typography.bodyMedium.copy(color = Color.White, textAlign = TextAlign.Right), modifier = Modifier.fillMaxWidth())
            }

            Column (
                modifier = Modifier
                    .background(color = LightBackground)
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 30.dp, start = 20.dp, end = 20.dp)
            ) {
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text("Account",style = MaterialTheme.typography.titleLarge.copy(color = PrimaryGreen, fontWeight = FontWeight.W500))

                    Spacer(modifier = Modifier.padding(start = 15.dp))
                    Row (
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .border(
                                width = 2.dp,
                                color = BorderGreen,
                                shape = RoundedCornerShape(6.dp)
                            )
                            .padding(vertical = 10.dp, horizontal = 12.dp)
                    ) {
                        Image(painter = painterResource(id = R.drawable.card_icon),
                            contentDescription = "Card Icon", modifier = Modifier.size(35.dp))
                        Spacer(modifier = Modifier.padding(start = 5.dp))
                        Text("Card",style = MaterialTheme.typography.bodyLarge.copy(color = PrimaryGreen, fontWeight = FontWeight.W500, fontSize = 18.sp))
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text("Category",style = MaterialTheme.typography.titleLarge.copy(color = PrimaryGreen, fontWeight = FontWeight.W500))

                    Spacer(modifier = Modifier.padding(start = 15.dp))
                    Row (
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .border(
                                width = 2.dp,
                                color = BorderGreen,
                                shape = RoundedCornerShape(6.dp)
                            )
                            .padding(vertical = 10.dp, horizontal = 12.dp)
                    ) {
                        Image(painter = painterResource(id = R.drawable.bills_icon),
                            contentDescription = "Card Icon", modifier = Modifier.size(35.dp))
                        Spacer(modifier = Modifier.padding(start = 5.dp))
                        Text("Bills",style = MaterialTheme.typography.bodyLarge.copy(color = PrimaryGreen, fontWeight = FontWeight.W500, fontSize = 18.sp))
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))
                Text("Electricity Bill",style = MaterialTheme.typography.titleLarge.copy(color = LightGreen, textAlign = TextAlign.Center), modifier = Modifier.fillMaxWidth())
            }
        }
    }
}
