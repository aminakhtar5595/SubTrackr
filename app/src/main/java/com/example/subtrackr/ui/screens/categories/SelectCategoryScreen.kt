package com.example.subtrackr.ui.screens.categories

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.subtrackr.data.datasource.expenseData
import com.example.subtrackr.ui.components.ButtonWithIcon
import com.example.subtrackr.ui.components.CategoryTag
import com.example.subtrackr.ui.theme.LightBlueGray
import com.example.subtrackr.ui.theme.PrimaryGreen

@Composable
fun SelectCategoryScreen(navController: NavController) {
    val categoryData = expenseData.categories
    Column(
        modifier = Modifier
            .padding(horizontal = 15.dp, vertical = 30.dp)
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
            contentDescription = "Back icon",
        )

        Spacer(modifier = Modifier.height(20.dp))
        Text(
            "Select category",
            style = MaterialTheme.typography.titleLarge.copy(color = PrimaryGreen, fontWeight = FontWeight.Bold),
        )

        Spacer(modifier = Modifier.height(5.dp))
        Text(
            "Select a category that best describes what you spent your money on.",
            style = MaterialTheme.typography.titleMedium.copy(color = PrimaryGreen),
        )

        Spacer(modifier = Modifier.height(25.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            items(categoryData.size) { index ->
                val category = categoryData[index]
//                CategoryTag(icon = category.icon, title = category.name,
//                    onPress = {  })
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.Cyan, shape = MaterialTheme.shapes.medium)
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    Image(painter = painterResource(id = category.icon), contentDescription = "$category.name Icon",
                        modifier = Modifier.size(30.dp))
                    Text(
                        category.name,
                        style = MaterialTheme.typography.bodyMedium.copy(color = PrimaryGreen, fontWeight = FontWeight.W500)
                    )
                }
            }
        }
    }
}