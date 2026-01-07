package com.shan.sportex.ui.components

import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.shan.sportex.R


@Composable
fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val items = listOf(
        BottomNavItem("matches", "Matches", R.drawable.ic_matches_active, R.drawable.ic_matches_inactive),
        BottomNavItem("feed", "Feed", R.drawable.ic_feed_active, R.drawable.ic_feed_inactive),
        BottomNavItem("series", "Series", R.drawable.ic_series_active, R.drawable.ic_series_inactive),
        BottomNavItem("profile", "Profile", R.drawable.ic_profile_active, R.drawable.ic_profile_inactive)
    )

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route
    val selectedIndex = items.indexOfFirst { it.route == currentRoute }.coerceAtLeast(0)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(92.dp)
            .clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp))
            .background(Color(0xFF0E0F11))
    ) {

        // 🔥 BAR-EDGE INDICATOR (CORRECT POSITION)
        if (currentRoute != null) {
            BarEdgeIndicator(
                selectedIndex = selectedIndex,
                itemCount = items.size
            )
        }

        // NAV ITEMS
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            items.forEach { item ->
                BottomNavItemView(
                    item = item,
                    selected = currentRoute == item.route
                ) {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            }
        }
    }
}



@Composable
private fun BarEdgeIndicator(
    selectedIndex: Int,
    itemCount: Int
) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .height(13.dp)
    ) {
        val segmentWidth = maxWidth / itemCount

        Box(
            modifier = Modifier
                .offset(
                    x = segmentWidth * selectedIndex + segmentWidth / 2 - 20.dp,
                    y = 0.dp
                )
                .width(40.dp)
                .height(13.dp)
        ) {


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .offset(y = (-6).dp)
                        .graphicsLayer {
                            renderEffect =
                                android.graphics.RenderEffect
                                    .createBlurEffect(
                                        120f,
                                        120f,
                                        android.graphics.Shader.TileMode.DECAL
                                    )
                                    .asComposeRenderEffect()
                        }
                        .background(Color(0xFFFFFDFE))
                )
            }

            // 🔹 SOLID LINE
            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .width(28.dp)
                    .height(3.dp)
                    .clip(RoundedCornerShape(50))
                    .background(Color(0xFFFFFDFE))
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
            .pointerInput(Unit) { detectTapGestures { onClick() } },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(13.dp)) // reserve indicator space

        Image(
            painter = painterResource(
                if (selected) item.activeIcon else item.inactiveIcon
            ),
            contentDescription = item.label,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = item.label,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            color = if (selected) Color.White else Color(0x66ACBED0)
        )
    }
}


private data class BottomNavItem(
    val route: String,
    val label: String,
    val activeIcon: Int,
    val inactiveIcon: Int
)