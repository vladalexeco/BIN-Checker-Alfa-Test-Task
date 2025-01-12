package com.example.binchecker.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.binchecker.core.navigation.AppNavScreen
import com.example.binchecker.core.navigation.CheckCardBin
import com.example.binchecker.presentation.ui.theme.BINCheckerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BINCheckerTheme {
                val navController = rememberNavController()

                AppNavScreen(
                    navController = navController,
                    startDestination = CheckCardBin.route
                )
            }
        }
    }
}