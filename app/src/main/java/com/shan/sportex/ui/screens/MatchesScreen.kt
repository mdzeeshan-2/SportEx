package com.shan.sportex.ui.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.layout.PaddingValues
import com.shan.sportex.ui.components.FilterChips
import com.shan.sportex.ui.components.MatchCard
import com.shan.sportex.ui.components.TopBar
import com.shan.sportex.viewmodel.MatchItem
import com.shan.sportex.viewmodel.MatchesViewModel

@Composable
fun MatchesScreen(viewModel: MatchesViewModel = viewModel()) {
    // use `by` with safe initial values so collectAsState is happy in every environment
    val matches by viewModel.visibleMatches.collectAsState(initial = emptyList())
    val currentFilter by viewModel.filter.collectAsState(initial = "All")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        TopBar()

        Spacer(modifier = Modifier.height(8.dp))

        FilterChips(
            items = listOf("Live", "All", "1h", "3h", "6h", "12h"),
            initialSelectedIndex = if (currentFilter.equals("Live", true)) 0 else 1,
            onSelectionChanged = { selected ->
                // send selection to the ViewModel
                viewModel.setFilter(selected)
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(vertical = 8.dp)) {
            items(matches, key = { it.id }) { item: MatchItem ->
                MatchCard(
                    teamA = item.teamA,
                    teamB = item.teamB,
                    scoreText = item.scoreText,
                    statusText = item.status,
                    initiallyFavorite = false,
                    onClick = { /* TODO navigate to details */ }
                )
            }
        }
    }
}
