package com.shan.sportex.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SegmentedFilterBar(
    items: List<String> = listOf("Live", "All", "1h", "3h", "6h", "12h"),
    selectedIndex: Int = 1,
    onSelectionChanged: (Int) -> Unit = {}
) {
    var currentIndex by remember { mutableStateOf(selectedIndex) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(56.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFF0F1113))
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEachIndexed { index, label ->

                val isSelected = index == currentIndex

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(6.dp))
                        .background(
                            if (isSelected) Color(0xFF3A3F44) else Color.Transparent
                        )
                        .clickable {
                            currentIndex = index
                            onSelectionChanged(index)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.body1,
                        color = if (isSelected) Color.White else Color(0xFF8E9499)
                    )
                }

                // Divider ONLY between Live and All
                if (index == 0) {
                    Spacer(modifier = Modifier.width(6.dp))
                    Box(
                        modifier = Modifier
                            .width(2.dp)
                            .height(14.dp)
                            .background(Color(0xFF2A2D30))
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                }
            }
        }
    }
}