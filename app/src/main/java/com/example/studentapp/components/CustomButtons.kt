package com.example.studentapp.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun DefaultButton(buttonText: String, onClick: () -> Unit, enabled: Boolean = true) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            enabled = enabled,
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .weight(1f),
            onClick = onClick
        ) {
            Text(text = buttonText)
        }
    }
}

@Composable
fun DefaultIconButton(modifier: Modifier, contentDescription: String, tint: Color, iconType: ImageVector, onClick: () -> Unit) {
    IconButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Icon(
            imageVector = iconType,
            contentDescription = contentDescription,
            tint = tint
        )
    }
}
