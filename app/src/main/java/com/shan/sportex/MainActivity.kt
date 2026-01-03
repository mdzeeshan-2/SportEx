package com.shan.sportex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.shan.sportex.ui.screens.MatchesScreen
import com.shan.sportex.ui.theme.SportExTheme
import com.shan.sportex.navigation.AppNavHost
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enables drawing edge-to-edge (status/nav bars transparent), same behaviour as your previous call
        enableEdgeToEdge()

        setContent {
            SportExTheme {
                AppNavHost()
            }
        }
    }
}
