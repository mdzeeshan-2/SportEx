package com.shan.sportex.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.layout.PaddingValues
import com.shan.sportex.ui.components.TopBar
import com.shan.sportex.ui.components.SegmentedFilterBar
import com.shan.sportex.ui.components.MatchListCard
import com.shan.sportex.viewmodel.MatchItem
import com.shan.sportex.viewmodel.MatchesViewModel
import com.shan.sportex.ui.components.MatchListCard2


@Composable
fun MatchesScreen(viewModel: MatchesViewModel = viewModel()) {

    val matches by viewModel.visibleMatches.collectAsState(initial = emptyList())
    val currentFilter by viewModel.filter.collectAsState(initial = "All")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        TopBar()

        Spacer(modifier = Modifier.height(8.dp))

        SegmentedFilterBar(
            selectedIndex = when (currentFilter.lowercase()) {
                "live" -> 0
                "all" -> 1
                else -> 1
            },
            onSelectionChanged = { index ->
                val filter = when (index) {
                    0 -> "Live"
                    1 -> "All"
                    2 -> "1h"
                    3 -> "3h"
                    4 -> "6h"
                    5 -> "12h"
                    else -> "All"
                }
                viewModel.setFilter(filter)
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            items(matches, key = { it.id }) {
                MatchListCard2()
            }
        }
    }
}