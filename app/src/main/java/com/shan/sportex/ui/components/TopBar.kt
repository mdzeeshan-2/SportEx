package com.shan.sportex.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.shan.sportex.R
import android.os.Build
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer

@Composable
fun TopBar() {

    // HEADER CONTAINER
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(102.dp)
            .background(
                Color(0xFFACBED0).copy(alpha = 0.05f), // rgba(172,190,208,0.1)
                shape = RoundedCornerShape(
                    bottomStart = 18.dp,
                    bottomEnd = 18.dp
                )
            )
            .border(
                width = 1.dp,
                color = Color(0x1AACBED0), // subtle bottom border
                shape = RoundedCornerShape(
                    bottomStart = 18.dp,
                    bottomEnd = 18.dp
                )
            )
    ) {

        // INNER TOP BAR ROW
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 54.dp,   // matches CSS padding-top
                    bottom = 12.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // LOGO ONLY (NO TEXT)
            Icon(
                painter = painterResource(id = R.drawable.ic_app_logo),
                contentDescription = "Sportex Logo",
                tint = Color.Unspecified,
                modifier = Modifier
                    .width(128.dp)
                    .height(34.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            // RIGHT ICON GROUP
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                GlassIconButton(
                    icon = R.drawable.ic_notification_logo
                )

                GlassIconButton(
                    icon = R.drawable.ic_search_logo
                )
            }
        }
    }
}

@Composable
private fun GlassIconButton(
    icon: Int
) {
    Box(
        modifier = Modifier
            .size(36.dp)
            .background(
                color = Color(0x26ACBED0), // rgba(172,190,208,0.15)
                shape = RoundedCornerShape(10.dp)
            )
            .border(
                width = 0.5.dp,
                color = Color(0x14ACBED0), // rgba(172,190,208,0.08)
                shape = RoundedCornerShape(10.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = Color(0xB3ACBED0), // rgba(172,190,208,0.7)
            modifier = Modifier.size(18.dp)
        )
    }
}

