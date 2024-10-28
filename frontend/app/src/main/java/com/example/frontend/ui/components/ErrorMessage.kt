package com.example.frontend.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ErrorMessage(
    message: String,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color(0xFFFFE6E6),
    textColor: Color = MaterialTheme.colorScheme.error
) {
    Box(
        modifier = modifier
            .padding(16.dp)
            .border(2.dp, textColor, RoundedCornerShape(8.dp))
            .background(backgroundColor)
            .padding(8.dp)
            .clickable { onDismiss() },
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.Warning,
                contentDescription = "Error Icon",
                tint = textColor
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = message,
                color = textColor,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}