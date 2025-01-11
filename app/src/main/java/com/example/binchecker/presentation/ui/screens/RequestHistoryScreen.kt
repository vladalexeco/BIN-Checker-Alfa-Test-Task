package com.example.binchecker.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.binchecker.presentation.ui.theme.BackgroundColor
import com.example.binchecker.presentation.ui.theme.MainTextColor
import com.example.binchecker.presentation.ui.views.checkcardbinscreen.CardInfoPlate
import com.example.binchecker.presentation.ui.views.checkcardbinscreen.mockCardInfo

@Composable
fun RequestHistoryScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BackgroundColor)
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "History of requests",
                style = TextStyle(fontSize = 14.sp, color = MainTextColor)
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 32.dp, top = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(listOf(mockCardInfo, mockCardInfo, mockCardInfo)) { cardInfo ->
                    CardInfoPlate(cardInfo = cardInfo)
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun RequestHistoryScreenPreview() {
    RequestHistoryScreen()
}