package com.example.subtrackr.ui.screens.expense

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import com.example.subtrackr.ui.theme.BorderGreen
import com.example.subtrackr.ui.theme.LightBackground
import com.example.subtrackr.ui.theme.LightGreen
import com.example.subtrackr.ui.theme.PlaceholderGray
import com.example.subtrackr.ui.theme.PrimaryGreen

@Composable
fun ExpenseOne() {
    var notesText by remember { mutableStateOf("") }
    Column() {
        // Header
        Text("Add new expense")
        Text("Enter the details of your expense to help you track your spending")

        // Amount section
        Text("Enter amount")
        TextField(
            value = notesText,
            onValueChange = { notesText = it },
            textStyle = TextStyle(fontSize = 20.sp, color = BorderGreen, fontWeight = FontWeight.SemiBold),
            placeholder = { Text("Enter amount", style = MaterialTheme.typography.titleLarge.copy(color = PlaceholderGray, fontSize = 20.sp, fontWeight = FontWeight.W500)) },
            modifier = Modifier
                .border(
                    width = 2.dp,
                    color = BorderGreen,
                    shape = RoundedCornerShape(6.dp)
                )
                .fillMaxWidth()
                .background(color = LightBackground)
        )

        // Description section
        Text("Enter description")
        TextField(
            value = notesText,
            onValueChange = { notesText = it },
            textStyle = TextStyle(fontSize = 20.sp, color = BorderGreen, fontWeight = FontWeight.SemiBold),
            placeholder = { Text("Enter description", style = MaterialTheme.typography.titleLarge.copy(color = PlaceholderGray, fontSize = 20.sp, fontWeight = FontWeight.W500)) },
            modifier = Modifier
                .border(
                    width = 2.dp,
                    color = BorderGreen,
                    shape = RoundedCornerShape(6.dp)
                )
                .fillMaxWidth()
                .background(color = LightBackground)
        )

        // Category section
        Text("Category")
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row() {
                Image(painter = painterResource(id = R.drawable.card_icon), contentDescription = "Category Icon")
                Text("Food")
            }
            Icon(
                imageVector = Icons.Filled.Check,
                contentDescription = "Back icon",
            )
        }

        // Date section
        Text("Date")
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("15th May, 2023")
            Icon(
                imageVector = Icons.Filled.Check,
                contentDescription = "Back icon",
            )
        }

        Button(
            onClick = {  },
            shape = RoundedCornerShape(5.dp),
            contentPadding = PaddingValues(horizontal = 40.dp, vertical = 12.dp),
            border = BorderStroke(2.dp, PrimaryGreen),
            colors = ButtonDefaults.buttonColors(contentColor = PrimaryGreen, containerColor = Color.Transparent),
        ) {
            Text("Add Expense",
                style = MaterialTheme.typography.titleSmall.copy(color = PrimaryGreen, fontWeight = FontWeight.W500, fontSize = 20.sp)
            )
        }
    }
}