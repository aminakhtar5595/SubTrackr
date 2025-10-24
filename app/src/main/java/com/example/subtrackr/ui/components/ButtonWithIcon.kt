package com.example.subtrackr.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.subtrackr.ui.theme.PrimaryGreen

@Composable
fun ButtonWithIcon(icon: ImageVector = Icons.Rounded.AddCircle, title: String = "ADD NEW CATEGORY", onClick: () -> Unit) {
    OutlinedButton(
        onClick = onClick,
        shape = RoundedCornerShape(5.dp),
        contentPadding = PaddingValues(horizontal = 15.dp, vertical = 12.dp),
        border = BorderStroke(2.dp, PrimaryGreen),
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "$title icon",
            modifier = Modifier.size(30.dp)
        )
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.W500,
                fontSize = 18.sp
            ),
            modifier = Modifier.padding(start = 10.dp)
        )
    }
}