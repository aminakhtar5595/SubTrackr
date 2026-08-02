package com.example.subtrackr.ui.screens.categories

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.subtrackr.data.datasource.expenseData
import com.example.subtrackr.ui.components.ButtonWithIcon
import com.example.subtrackr.ui.components.CategoryTag
import com.example.subtrackr.ui.theme.PrimaryGreen

@Composable
fun SelectCategoryScreen(navController: NavController) {
    val categoryData = expenseData.categories
    Column() {
        Icon(
            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
            contentDescription = "Back icon",
//            modifier = Modifier.size(30.dp)
        )
        Text(
            "Select category",
            style = MaterialTheme.typography.titleLarge.copy(color = PrimaryGreen, fontWeight = FontWeight.Bold),
//            modifier = Modifier.padding(end = 10.dp)
        )

        Text(
            "Select a category that best describes what you spent your money on.",
            style = MaterialTheme.typography.titleMedium.copy(color = PrimaryGreen),
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(categoryData.size) { index ->
                val category = categoryData[index]
                CategoryTag(icon = category.icon, title = category.name,
                    onPress = {  })
            }
        }
    }
}