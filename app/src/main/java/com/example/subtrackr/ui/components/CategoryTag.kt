package com.example.subtrackr.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.subtrackr.ui.theme.PrimaryGreen

@Composable
fun CategoryTag(icon: Int, title: String, onPress: () -> Unit) {
    Column (
        modifier = Modifier.clickable { onPress() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = icon), contentDescription = "$title Icon")
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            title,
            style = MaterialTheme.typography.bodyLarge.copy(color = PrimaryGreen, fontWeight = FontWeight.W500)
        )
    }
}