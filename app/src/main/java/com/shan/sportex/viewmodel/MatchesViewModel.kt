package com.shan.sportex.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shan.sportex.ui.components.SimpleTeam
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class MatchItem(
    val id: String,
    val teamA: SimpleTeam,
    val teamB: SimpleTeam,
    val scoreText: String = "—",
    val status: String = "UPCOMING"
)

class MatchesViewModel : ViewModel() {

    private val _allMatches = MutableStateFlow<List<MatchItem>>(emptyList())
    private val _filter = MutableStateFlow("All")
    private val _visibleMatches = MutableStateFlow<List<MatchItem>>(emptyList())

    val filter: StateFlow<String> = _filter.asStateFlow()
    val visibleMatches: StateFlow<List<MatchItem>> = _visibleMatches.asStateFlow()

    init {
        loadSampleMatches()
        applyFilter(_filter.value)
    }

    private fun loadSampleMatches() {
        val sample = listOf(
            MatchItem(
                id = "1",
                teamA = SimpleTeam("India", "IND"),
                teamB = SimpleTeam("Australia", "AUS"),
                scoreText = "120/4 (15.3)",
                status = "LIVE"
            ),
            MatchItem(
                id = "2",
                teamA = SimpleTeam("England", "ENG"),
                teamB = SimpleTeam("Pakistan", "PAK"),
                scoreText = "—",
                status = "UPCOMING"
            ),
            MatchItem(
                id = "3",
                teamA = SimpleTeam("Rival FC", "RFC"),
                teamB = SimpleTeam("United SC", "USC"),
                scoreText = "210/7 (50.0)",
                status = "FINISHED"
            )
        )
        _allMatches.value = sample
    }

    fun setFilter(label: String) {
        viewModelScope.launch {
            _filter.value = label
            applyFilter(label)
        }
    }

    private fun applyFilter(label: String) {
        val filtered = when (label.lowercase()) {
            "live" -> _allMatches.value.filter { it.status.equals("LIVE", ignoreCase = true) }
            "upcoming" -> _allMatches.value.filter { it.status.equals("UPCOMING", ignoreCase = true) }
            "finished" -> _allMatches.value.filter { it.status.equals("FINISHED", ignoreCase = true) }
            "all" -> _allMatches.value
            else -> _allMatches.value
        }
        _visibleMatches.value = filtered
    }
}
