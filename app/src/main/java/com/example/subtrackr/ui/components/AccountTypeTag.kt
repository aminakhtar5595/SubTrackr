package com.example.subtrackr.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.subtrackr.ui.theme.PrimaryGreen
import com.example.subtrackr.ui.theme.PrimaryRed

@Composable
fun AccountTypeTag(icon: Int, title: String, amount: String, navigate: () -> Unit) {
    Column(modifier = Modifier.fillMaxWidth().clickable { navigate() }) {
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(painter = painterResource(id = icon), contentDescription = "$title Icon")
            Spacer(modifier = Modifier.width(15.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge.copy(
                    color = PrimaryGreen,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W500
                ),
                modifier = Modifier.weight(1f)
            )
            Text(
                "-$$amount",
                style = MaterialTheme.typography.titleMedium.copy(
                    color = PrimaryRed,
                    fontWeight = FontWeight.W500,
                    fontSize = 18.sp
                )
            )
        }
    }
}