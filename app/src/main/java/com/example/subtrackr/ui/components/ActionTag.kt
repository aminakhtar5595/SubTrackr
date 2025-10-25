package com.example.subtrackr.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.subtrackr.ui.theme.LightGreen

@Composable
fun ActionTag(icon: ImageVector, iconDescription: String, title: String, onClick: () -> Unit) {
    Row (
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier.clickable { onClick() }
    ) {
        Icon(
            imageVector = icon,
            contentDescription = iconDescription,
            modifier = Modifier.size(27.dp),
            tint = LightGreen
        )
        Text(title,style = MaterialTheme.typography.titleLarge.copy(color = LightGreen, fontSize = 18.sp, fontWeight = FontWeight.Bold), modifier = Modifier.padding(start = 8.dp))
    }
}