package com.shan.sportex.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.shan.sportex.ui.theme.GreenAccent
import com.shan.sportex.ui.theme.DimText
import com.shan.sportex.ui.theme.LightText
import androidx.compose.material.MaterialTheme

@Composable
fun FilterChips(
    modifiers: Modifier = Modifier,
    items: List<String> = listOf("Live", "All", "1h", "3h", "6h", "12h"),
    initialSelectedIndex: Int = 0,
    onSelectionChanged: (selected: String) -> Unit = {}
) {
    var selectedIndex by remember { mutableStateOf(initialSelectedIndex) }

    LazyRow(
        modifier = modifiers
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        contentPadding = PaddingValues(horizontal = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items) { label ->
            val index = items.indexOf(label)
            val selected = index == selectedIndex

            Surface(
                modifier = Modifier
                    .wrapContentWidth()
                    .height(36.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .clickable {
                        selectedIndex = index
                        onSelectionChanged(label)
                    },
                color = if (selected) GreenAccent else MaterialTheme.colors.surface,
                elevation = if (selected) 4.dp else 0.dp
            ) {
                Box(
                    modifier = Modifier
                        .padding(horizontal = 14.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.body2,
                        color = if (selected) MaterialTheme.colors.onPrimary else DimText
                    )
                }
            }
        }
    }
}
