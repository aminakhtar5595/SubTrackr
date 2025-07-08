package com.example.subtrackr.ui.screens.categories
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Info
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
import com.example.subtrackr.ui.theme.BorderGreen
import com.example.subtrackr.ui.theme.PrimaryGreen

@Composable
fun CategoryDetailScreen(navController: NavController) {
    Row(
        modifier = Modifier.padding(20.dp)
    ) {
        Icon(
            imageVector = Icons.Outlined.Clear,
            contentDescription = "Menu Icon",
            modifier = Modifier.size(30.dp),
            tint = PrimaryGreen,
        )

        Text("Category details", style = MaterialTheme.typography.titleLarge.copy(color = PrimaryGreen, fontSize = 18.sp, fontWeight = FontWeight.W500))
    }
}