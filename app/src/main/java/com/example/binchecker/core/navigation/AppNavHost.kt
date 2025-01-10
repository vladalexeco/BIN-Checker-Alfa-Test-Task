package com.example.binchecker.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.binchecker.presentation.ui.screens.CheckCardBinScreen
import com.example.binchecker.presentation.ui.screens.RequestHistoryScreen

@Composable
fun AppNavHost(
    navHostController: NavHostController,
    startDestination: String,
    modifier: Modifier
) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination
    ) {
        composable(route = CheckCardBin.route) {
            CheckCardBinScreen()
        }

        composable(route = RequestHistory.route) {
            RequestHistoryScreen()
        }
    }
}