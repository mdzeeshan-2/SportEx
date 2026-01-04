package com.shan.sportex.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun GlassMatchCard(
    modifier: Modifier = Modifier,
    glowColor: Color,          // ← derived from team logo
    leftTeam: String = "MO-W",
    rightTeam: String = "NS-W"
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {

        // 🔥 GLOW LAYER (behind the card)
        Box(
            modifier = Modifier
                .matchParentSize()
                .blur(40.dp)
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            glowColor.copy(alpha = 0.45f),
                            Color.Transparent
                        ),
                        radius = 600f
                    )
                )
        )

        // 🧊 GLASS CARD
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(24.dp)),
            color = Color.Transparent,
            elevation = 0.dp
        ) {
            Box(
                modifier = Modifier
                    .background(
                        Brush.linearGradient(
                            colors = listOf(
                                Color.White.copy(alpha = 0.08f),
                                Color.White.copy(alpha = 0.02f)
                            )
                        )
                    )
                    .padding(16.dp)
            ) {

                Column {

                    // Header
                    Text(
                        text = "LIVE · ODI · 2nd Match",
                        color = Color(0xFFB0B6BC),
                        fontWeight = FontWeight.Medium
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Teams + score
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(leftTeam, color = Color.White)

                        Spacer(modifier = Modifier.weight(1f))

                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                "117-5  ·  104-5",
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                "100 b      93 b",
                                color = Color(0xFF9BA0A5)
                            )
                        }

                        Spacer(modifier = Modifier.weight(1f))

                        Text(rightTeam, color = Color.White)
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // Footer
                    Text(
                        "107 runs need in 100 balls",
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        color = Color(0xFF9BA0A5)
                    )
                }
            }
        }
    }
}