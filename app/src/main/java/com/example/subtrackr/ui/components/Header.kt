package com.example.subtrackr.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.subtrackr.R
import com.example.subtrackr.ui.theme.PrimaryGreen
import com.example.subtrackr.ui.theme.PrimaryRed

@Composable
fun Header(onClickSearch: () -> Unit) {
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
                modifier = Modifier.size(30.dp).clickable { onClickSearch() },
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
}