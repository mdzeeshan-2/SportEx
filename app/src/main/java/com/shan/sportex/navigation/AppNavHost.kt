package com.shan.sportex.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shan.sportex.ui.components.BottomBar
import com.shan.sportex.ui.screens.MatchesScreen
import com.shan.sportex.ui.screens.PlaceholderScreen
import com.shan.sportex.ui.theme.SportExTheme

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Matches.route,
            modifier = Modifier.padding(padding)
        ) {
            composable(Screen.Matches.route) {
                MatchesScreen()
            }
            composable(Screen.Feed.route) {
                PlaceholderScreen(title = "Feed")
            }
            composable(Screen.Series.route) {
                PlaceholderScreen(title = "Series")
            }
            composable(Screen.Profile.route) {
                PlaceholderScreen(title = "Profile")
            }
        }
    }
}
