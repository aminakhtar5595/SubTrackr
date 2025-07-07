package com.example.subtrackr.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.subtrackr.ui.theme.PrimaryGreen

@Composable
fun FloatingButton(onClick: () -> Unit) {
    SmallFloatingActionButton(
        onClick = { onClick() },
        containerColor = Color.White,
        contentColor = PrimaryGreen,
        shape = CircleShape,
        modifier = Modifier.size(50.dp)
    ) {
        Icon(Icons.Filled.Add, "Add expense", modifier = Modifier.size(30.dp))
    }
}