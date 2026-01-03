package com.shan.sportex.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.ViewModule
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Matches : Screen("matches", "Matches", Icons.Default.Place)
    object Feed : Screen("feed", "Feed", Icons.Default.ViewModule)
    object Series : Screen("series", "Series", Icons.Default.List)
    object Profile : Screen("profile", "Profile", Icons.Default.Person)
}
