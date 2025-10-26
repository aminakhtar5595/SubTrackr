package com.example.subtrackr.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.subtrackr.ui.theme.BorderGreen

@Composable
fun AccountTag(title: String, icon: Int, iconDescription: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(title, style = MaterialTheme.typography.titleLarge.copy(color = BorderGreen, fontSize = 18.sp, fontWeight = FontWeight.W500))
        Spacer(modifier = Modifier.height(5.dp))
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .border(
                    width = 2.dp,
                    color = BorderGreen,
                    shape = RoundedCornerShape(6.dp)
                )
                .padding(horizontal = 12.dp)
                .clickable { onClick() },
            horizontalArrangement = Arrangement.Center
        ) {
            Image(painter = painterResource(id = icon),
                contentDescription = iconDescription, modifier = Modifier.size(35.dp))
            Text(title, style = MaterialTheme.typography.titleLarge.copy(color = BorderGreen, fontWeight = FontWeight.W500), modifier = Modifier.padding(start = 8.dp))
        }
    }
}