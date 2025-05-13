package com.nplmrmanoj.simplenoteapp.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@androidx.compose.runtime.Composable
fun SimpleText(
    text: String,
    color: Color,
    isSelected: Boolean = false,
    onClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .border(1.dp, color, shape = RoundedCornerShape(12.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                onClick()
            }
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            if (isSelected) {
                Icon(imageVector = Icons.Default.Check, contentDescription = null, tint = color)
            }
            Text(
                text,
                color = color,
            )
        }
    }


}