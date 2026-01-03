package com.shan.sportex.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.shan.sportex.ui.theme.DarkCard
import com.shan.sportex.ui.theme.DimText
import com.shan.sportex.ui.theme.LightText

data class SimpleTeam(
    val name: String,
    val short: String // short label for avatar fallback (e.g. "IND")
)

@Composable
fun MatchCard(
    modifier: Modifier = Modifier,
    teamA: SimpleTeam,
    teamB: SimpleTeam,
    scoreText: String = "—",
    statusText: String = "UPCOMING", // LIVE / UPCOMING / FINISHED
    initiallyFavorite: Boolean = false,
    onClick: () -> Unit = {}
) {
    var favorite by remember { mutableStateOf(initiallyFavorite) }

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 6.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick() },
        color = DarkCard,
        elevation = 2.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Left: Team info
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Team A avatar fallback
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(MaterialTheme.colors.primary),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(teamA.short, style = MaterialTheme.typography.body2, color = LightText)
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Column {
                        Text(
                            text = teamA.name,
                            style = MaterialTheme.typography.body1,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = LightText
                        )
                        Text(text = scoreText, style = MaterialTheme.typography.caption, color = DimText)
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Team B avatar fallback
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(MaterialTheme.colors.secondary),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(teamB.short, style = MaterialTheme.typography.body2, color = LightText)
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Column {
                        Text(
                            text = teamB.name,
                            style = MaterialTheme.typography.body1,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = LightText
                        )
                        // You can show second team's score if any
                    }
                }
            }

            // Right: status + favorite
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text(text = statusText, style = MaterialTheme.typography.caption, color = DimText)

                Spacer(modifier = Modifier.height(8.dp))

                IconButton(onClick = { favorite = !favorite }) {
                    Icon(
                        imageVector = if (favorite) Icons.Default.Star else Icons.Default.StarBorder,
                        contentDescription = "Favorite",
                        tint = if (favorite) MaterialTheme.colors.primary else DimText
                    )
                }
            }
        }
    }
}
