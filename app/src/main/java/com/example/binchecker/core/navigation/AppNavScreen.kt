package com.example.binchecker.core.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.binchecker.presentation.ui.views.bottomnavigationbar.CustomBottomNavBar

@Composable
fun AppNavScreen(
    navController: NavHostController,
    startDestination: String
) {
    Scaffold(
        bottomBar = { BottomBar(navHostController = navController) },
    ) {
        AppNavHost(
            navHostController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(it)
        )
    }
}

@Composable
@Preview
fun AppNavScreenPreview() {
    AppNavScreen(
        navController = rememberNavController(),
        startDestination = CheckCardBin.route
    )
}

@Composable
fun BottomBar(
    navHostController: NavHostController
) {
    CustomBottomNavBar(
        screenRoute1 = CheckCardBin.route,
        screenRoute2 = RequestHistory.route,
        currentRoute = currentRoute(navController = navHostController),
        firstItemClick = {
            navHostController.navigate(CheckCardBin.route) {
                popUpTo(navHostController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        },
        secondItemClick = {
            navHostController.navigate(RequestHistory.route) {
                popUpTo(navHostController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}