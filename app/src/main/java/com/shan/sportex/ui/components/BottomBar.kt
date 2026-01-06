package com.shan.sportex.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.foundation.Image
import com.shan.sportex.R
import androidx.compose.ui.composed
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.drawscope.Stroke



@Composable
fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val items = listOf(
        BottomNavItem(
            route = "matches",
            label = "Matches",
            activeIcon = R.drawable.ic_matches_active,
            inactiveIcon = R.drawable.ic_matches_inactive
        ),
        BottomNavItem(
            route = "feed",
            label = "Feed",
            activeIcon = R.drawable.ic_feed_active,
            inactiveIcon = R.drawable.ic_feed_inactive
        ),
        BottomNavItem(
            route = "series",
            label = "Series",
            activeIcon = R.drawable.ic_series_active,
            inactiveIcon = R.drawable.ic_series_inactive
        ),
        BottomNavItem(
            route = "profile",
            label = "Profile",
            activeIcon = R.drawable.ic_profile_active,
            inactiveIcon = R.drawable.ic_profile_inactive
        )
    )

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(92.dp)
            .clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp))
            .background(Color(0xFF0E0F11))
            .padding(horizontal = 12.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        items.forEach { item ->
            BottomNavItemView(
                item = item,
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}

@Composable
private fun BottomNavItemView(
    item: BottomNavItem,
    selected: Boolean,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .width(87.75.dp)
            .height(71.dp)
            .padding(bottom = 6.dp)
            .noRippleClickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // ACTIVE INDICATOR
        Box(
            modifier = Modifier
                .height(13.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.TopCenter
        ) {
            if (selected) {
                Box(
                    modifier = Modifier
                        .width(28.dp)
                        .height(3.dp)
                        .clip(RoundedCornerShape(50))
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color(0xFFFFFFFF),
                                    Color(0xFFB9C7D6)
                                )
                            )
                        )
                        .drawBehind {
                            drawRect(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color(0x66FFFFFF),
                                        Color.Transparent
                                    )
                                ),
                                size = size
                            )
                        }
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Image(
            painter = painterResource(
                if (selected) item.activeIcon else item.inactiveIcon
            ),
            contentDescription = item.label,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = item.label,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            color = if (selected)
                Color(0xFFFFFFFF)
            else
                Color(0x66ACBED0)
        )
    }
}

private data class BottomNavItem(
    val route: String,
    val label: String,
    val activeIcon: Int,
    val inactiveIcon: Int
)


private fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier =
    composed {
        this.then(
            Modifier
                .pointerInput(Unit) {
                    detectTapGestures {
                        onClick()
                    }
                }
        )
    }