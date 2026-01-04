package com.shan.sportex.ui.components

import android.graphics.BlurMaskFilter
import android.graphics.Paint as AndroidPaint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.shan.sportex.R
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb


@Composable
fun MatchListCard(modifier: Modifier = Modifier) {

    var isFavorite by remember { mutableStateOf(false) }

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp),
        shape = RoundedCornerShape(15.dp),
        color = Color.Transparent,
        elevation = 0.dp
    ) {

        // 🔹 ROOT CONTAINER
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(15.dp))
                .background(
                    Brush.linearGradient(
                        listOf(
                            Color(0x33FFFFFF),
                            Color(0x22000000),
                            Color(0x66000000)
                        )
                    )
                )
        ) {

            // 🔹 GLASS LIGHT LAYER (ONLY THIS HAS CANVAS)
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .drawWithCache {

                        val leftLight = AndroidPaint().apply {
                            color = android.graphics.Color.argb(90, 125, 6, 0)
                            maskFilter =
                                BlurMaskFilter(100f, BlurMaskFilter.Blur.NORMAL)
                        }

                        val rightLight = AndroidPaint().apply {
                            color = android.graphics.Color.argb(50, 180, 159, 44)
                            maskFilter =
                                BlurMaskFilter(100f, BlurMaskFilter.Blur.NORMAL)
                        }

                        onDrawBehind {

                            drawIntoCanvas { canvas ->
                                canvas.nativeCanvas.drawCircle(
                                    -size.width * 0.20f,
                                    size.height * 0.38f,
                                    size.width * 0.45f,
                                    leftLight
                                )
                            }

                            drawIntoCanvas { canvas ->
                                canvas.nativeCanvas.drawCircle(
                                    size.width * 1.20f,
                                    size.height * 0.35f,
                                    size.width * 0.50f,
                                    rightLight
                                )
                            }
                        }
                    }
            )

            // 🔹 CONTENT LAYER (ON TOP OF GLASS)
            Column(
                modifier = Modifier
                    .background(
                        Brush.linearGradient(
                            listOf(
                                Color(0xAA0B0F12),
                                Color(0xAA11161A)
                            )
                        )
                    )
                    .padding(18.dp)
            ) {

                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("● LIVE", color = Color(0xFFFF5A5A), style = MaterialTheme.typography.caption)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("• ODI • 2nd Match", color = Color(0xFFB0B6BB), style = MaterialTheme.typography.caption)

                    Spacer(modifier = Modifier.weight(1f))

                    IconButton(onClick = { isFavorite = !isFavorite }) {
                        Icon(
                            imageVector = if (isFavorite)
                                Icons.Filled.Star
                            else
                                Icons.Outlined.StarBorder,
                            contentDescription = null,
                            tint = Color(0xFFE0E6EB)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(22.dp))

                // Teams & Score
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            painter = painterResource(R.drawable.liverpool_fc_seeklogo),
                            contentDescription = null,
                            modifier = Modifier.size(44.dp)
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text("MO-W", color = Color.White)
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {

                        Row {
                            Text(
                                text = "117-5 : ",
                                color = Color(0xFFB0B6BB),
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "104-5",
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            "100 b      93 b",
                            color = Color(0xFFB0B6BB),
                            style = MaterialTheme.typography.caption
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            painter = painterResource(R.drawable.manchester),
                            contentDescription = null,
                            modifier = Modifier.size(44.dp)
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text("NS-W", color = Color.White)
                    }
                }

                Spacer(modifier = Modifier.height(26.dp))

                // 🔹 ODDS — CLEAN, NO GLASS OVERLAY
                Row(modifier = Modifier.fillMaxWidth()) {
                    SolidOdds("MO-W", "1.96", Modifier.weight(1f))
                    Spacer(modifier = Modifier.width(12.dp))
                    SolidOdds("Draw", "3.40", Modifier.weight(1f))
                    Spacer(modifier = Modifier.width(12.dp))
                    SolidOdds("NS-W", "4.20", Modifier.weight(1f))
                }

                Spacer(modifier = Modifier.height(22.dp))

                Text(
                    "107 runs need in 100 balls",
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    color = Color(0xFFB0B6BB)
                )
            }
        }
    }
}

@Composable
private fun SolidOdds(
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0x14ACBED0))

            .padding(vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(title, color = Color(0xFF8E9499), style = MaterialTheme.typography.caption)
        Spacer(modifier = Modifier.height(6.dp))
        Text(value, color = Color.White, fontWeight = FontWeight.Bold)
    }
}