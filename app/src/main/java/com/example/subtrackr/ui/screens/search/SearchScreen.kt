package com.example.subtrackr.ui.screens.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
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
import com.example.subtrackr.ui.theme.BorderGreen
import com.example.subtrackr.ui.theme.LightBackground
import com.example.subtrackr.ui.theme.PlaceholderGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen() {
    var searchText by remember { mutableStateOf("") }
    val foundResult by remember { mutableStateOf(true) }
    val searchRecords by remember { mutableStateOf(true) }
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
    ) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .background(color = LightBackground)
            .border(width = 2.dp, color = BorderGreen, shape = RoundedCornerShape(10.dp))
            .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically
    ) {
        Image(painter = painterResource(id = R.drawable.search_green_icon),
            contentDescription = "Search Icon", modifier = Modifier.size(30.dp))

        TextField(
            value = searchText,
            onValueChange = { searchText = it },
//            colors = TextFieldDefaults.textFieldColors(
//                containerColor = Color.Transparent,
//                textColor = Color.Gray,
//                focusedIndicatorColor = Color.Transparent,
//                unfocusedIndicatorColor = Color.Transparent,
//                placeholderColor = Color.LightGray
//            ),
            textStyle = TextStyle(
                fontSize = 20.sp, color = BorderGreen, fontWeight = FontWeight.SemiBold),
            placeholder = { Text("Search for records",
                style = MaterialTheme.typography.titleLarge.copy(color = PlaceholderGray, fontSize = 20.sp)
            ) }
        )
    }

    Column(
        modifier = Modifier.fillMaxWidth().padding(vertical = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(if (foundResult) "Total 3 matches found" else "No matches found",style = MaterialTheme.typography.titleLarge.copy(color = BorderGreen, fontSize = 18.sp, fontWeight = FontWeight.W500))
    }

    if (!searchRecords) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(painter = painterResource(id = R.drawable.notes_icon),
                contentDescription = "Searching Notes Icon", modifier = Modifier.size(70.dp))
            Spacer(modifier = Modifier.height(20.dp))
            Text("Search records by notes, category name and account name",style = MaterialTheme.typography.titleMedium.copy(color = BorderGreen, fontSize = 18.sp, textAlign = TextAlign.Center))
        }
    }
}
}