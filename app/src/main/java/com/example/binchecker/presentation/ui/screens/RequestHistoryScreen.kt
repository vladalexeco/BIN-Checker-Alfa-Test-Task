package com.example.binchecker.presentation.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun RequestHistoryScreen() {
    Text(
        text = "This is request history screen"
    )
}

@Composable
@Preview(showBackground = true)
fun RequestHistoryScreenPreview() {
    RequestHistoryScreen()
}