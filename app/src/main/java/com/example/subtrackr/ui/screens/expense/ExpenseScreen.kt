package com.example.subtrackr.ui.screens.expense

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.verticalScroll
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
fun ExpenseScreen() {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFfffde8))
            .padding(20.dp),
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
    }
}