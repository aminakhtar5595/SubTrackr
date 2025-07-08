package com.example.subtrackr.ui.screens.categories
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Divider
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
import com.example.subtrackr.R
import com.example.subtrackr.ui.theme.BorderGreen
import com.example.subtrackr.ui.theme.DarkBackground
import com.example.subtrackr.ui.theme.LightBackground
import com.example.subtrackr.ui.theme.LightGray
import com.example.subtrackr.ui.theme.PrimaryGreen

@Composable
fun CategoryDetailScreen(navController: NavController) {
    Column (
        modifier = Modifier
            .background(color = LightBackground)
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.Clear,
                contentDescription = "Menu Icon",
                modifier = Modifier.size(30.dp),
                tint = PrimaryGreen,
            )

            Column {
                Text("Category details", style = MaterialTheme.typography.titleLarge.copy(color = PrimaryGreen, fontWeight = FontWeight.W500), modifier = Modifier.padding(bottom = 5.dp))
                Text("Records: All time", style = MaterialTheme.typography.titleMedium.copy(color = BorderGreen))
            }
        }
        Divider(color = LightGray, thickness = 3.dp)

        Column (
            modifier = Modifier.padding(20.dp)
        ) {
            Row (
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.food_icon),
                    contentDescription = "App Icon",
                )

                Column {
                    Text("Food", style = MaterialTheme.typography.titleLarge.copy(color = PrimaryGreen, fontWeight = FontWeight.W500), modifier = Modifier.padding(bottom = 5.dp))
                    Text("Expense category", style = MaterialTheme.typography.titleMedium.copy(color = BorderGreen))
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .border(2.dp, color = LightGray, RoundedCornerShape(6.dp))
                    .padding(12.dp)
                    .background(color = DarkBackground)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "Menu Icon",
                    modifier = Modifier.size(30.dp),
                    tint = BorderGreen,
                )
                Text("You can see Monthly, weekly, or daily statistics in the Analysis section.", style = MaterialTheme.typography.titleLarge.copy(color = BorderGreen, fontSize = 18.sp, fontWeight = FontWeight.W500), modifier = Modifier.padding(start = 15.dp))
            }
        }
    }

}